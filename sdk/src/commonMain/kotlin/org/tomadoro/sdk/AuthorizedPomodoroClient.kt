package org.tomadoro.sdk

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.core.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.plus
import org.tomadoro.sdk.internal.toResult
import org.tomadoro.sdk.results.*
import org.tomadoro.sdk.results.serializer.ResultsSerializersModule
import org.tomadoro.sdk.types.TimerSessionCommand
import org.tomadoro.sdk.types.TimerSettings
import org.tomadoro.sdk.types.TimerUpdate
import org.tomadoro.sdk.types.serializer.TypesSerializersModule
import org.tomadoro.sdk.types.value.*

/**
 * Pomodoro API Client.
 */
public class AuthorizedPomodoroClient(
    private val apiUrl: String = "https://pomodoro.y9vad9.com",
    private var accessToken: AccessToken
) {
    private val client = HttpClient {
        val json = Json {
            @OptIn(ExperimentalSerializationApi::class)
            explicitNulls = false
            serializersModule = ResultsSerializersModule +
                TypesSerializersModule
        }

        defaultRequest {
            url(apiUrl)
            header(HttpHeaders.Authorization, accessToken.string)
            contentType(ContentType.parse("application/json"))
        }

        install(HttpTimeout) {
            connectTimeoutMillis = 15000L
            requestTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }

        install(WebSockets) {
            pingInterval = 25000L
            contentConverter = KotlinxWebsocketSerializationConverter(json)
        }

        install(Logging)

        install(ContentNegotiation) {
            json(json)
        }
    }

    /**
     * Checks whether host is available or not.
     */
    public suspend fun ok(): Boolean = client.get("ok").status.isSuccess()

    /**
     * Gets user id by [accessToken].
     */
    public suspend fun getUserId(): GetUserIdResult {
        return client.post("auth/user-id").toResult()
    }

    /**
     * Removes current authorization by [accessToken].
     */
    public suspend fun unauthorize(): RemoveTokenResult {
        return client.delete("auth").toResult()
    }

    /**
     * Renews access token by [refreshToken].
     * Old token will no longer usable.
     *
     * This instance will keep working, accessToken will be automatically updated.
     */
    public suspend fun renewToken(refreshToken: RefreshToken): RenewTokenResult {
        return client.post("auth/renew") {
            parameter("refresh_token", refreshToken.string)
        }.toResult<RenewTokenResult>().also {
            if (it is RenewTokenResult.Success)
                accessToken = it.accessToken
        }
    }

    /**
     * Creates timer by [accessToken].
     * @param name name of a timer (max 50 symbols)
     * @param settings timer settings
     */
    public suspend fun createTimer(
        name: Name,
        settings: TimerSettings
    ): CreateTimerResult {
        return client.post("timers") {
            setBody(CreateTimerRequest(name = name, settings = settings))
        }.toResult()
    }

    /**
     * Gets timer by [timerId].
     * @param timerId unique identifier of a timer.
     */
    public suspend fun getTimer(
        timerId: TimerId
    ): GetTimerResult {
        return client.get("timers") {
            parameter("id", timerId.int)
        }.toResult()
    }

    /**
     * Gets user's timers by [accessToken].
     */
    public suspend fun getTimers(
        count: Count,
        offset: Offset
    ): GetTimersResult {
        return client.get("timers/all") {
            parameter("count", count.int)
            parameter("offset", offset.int)
        }.toResult()
    }

    /**
     * Removes timer by [timerId] with [accessToken].
     * @param timerId unique identifier of a timer.
     */
    public suspend fun removeTimer(

        timerId: TimerId
    ): RemoveTimerResult {
        return client.delete("timers") {

            parameter("id", timerId.int)
        }.toResult()
    }

    /**
     * Sets new timer settings.
     * @param timerId unique identifier of a timer.
     */
    public suspend fun setTimerSettings(
        timerId: TimerId,
        patch: TimerSettings.Patch
    ): SetTimerSettingsResult {
        return client.patch("timers") {

            parameter("id", timerId.int)
            setBody(patch)
        }.toResult()
    }

    /**
     * Starts timer with [timerId].
     * @param timerId unique identifier of a timer.
     */
    public suspend fun startTimer(

        timerId: TimerId
    ): StartTimerResult {
        return client.post("timers/start") {

            parameter("id", timerId.int)
        }.toResult()
    }

    /**
     * Creates invite for [timerId].
     * @param timerId unique identifier of a timer.
     * @param max max count of joiners.
     */
    public suspend fun createInvite(
        timerId: TimerId,
        max: Count
    ): CreateInviteResult {
        return client.post("timers/invites") {

            parameter("timer_id", timerId.int)
            parameter("max_joiners", max.int)
        }.toResult()
    }

    /**
     * Gets all invites for [timerId].
     * @param timerId unique identifier of a timer.
     */
    public suspend fun getInvites(
        timerId: TimerId
    ): GetInvitesResult {
        return client.get("timers/invites/all") {
            parameter("timer_id", timerId.int)
        }.toResult()
    }

    /**
     * Joins by [code] to timer.
     * @param code invite code
     */
    public suspend fun joinTimerByInviteCode(
        code: Code
    ): JoinByCodeResult {
        return client.post("timers/invites/join") {
            parameter("code", code.string)
        }.toResult()
    }

    /**
     * Joins by [code] to timer.
     * @param code invite code
     */
    public suspend fun removeInvite(
        code: Code
    ): RemoveInviteResult {
        return client.delete("timers/invites") {
            parameter("code", code.string)
        }.toResult()
    }

    public suspend fun getTimerUpdates(
        timerId: TimerId,
        commands: Flow<TimerSessionCommand>,
        scope: CoroutineScope
    ): Flow<TimerUpdate> {
        val sharedFlow = MutableSharedFlow<TimerUpdate>(3)
        val ws = client.webSocketSession(
            host = "0.0.0.0",
            port = 9090,
            path = "timers/track",
            method = HttpMethod.Get
        ) {
            parameter("timer_id", timerId.int)
        }

        scope.launch {
            while (isActive) {
                sharedFlow.emit(ws.receiveDeserialized())
            }
        }

        scope.launch {
            commands.collectLatest {
                ws.sendSerialized(it)
            }
        }
        return sharedFlow.asSharedFlow()
    }

    public fun close(): Unit = client.close()

    @Suppress("unused")
    @Serializable
    private class CreateTimerRequest(
        val name: Name,
        val settings: TimerSettings,
    )
}