package io.timemates.api.rsocket

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.HttpStatusCode
import io.rsocket.kotlin.RSocketError
import io.rsocket.kotlin.core.WellKnownMimeType
import io.rsocket.kotlin.keepalive.KeepAlive
import io.rsocket.kotlin.ktor.client.RSocketSupport
import io.rsocket.kotlin.ktor.client.rSocket
import io.rsocket.kotlin.payload.PayloadMimeType
import io.timemates.api.authorizations.AuthorizationServiceApi
import io.timemates.api.timers.TimerSessionsServiceApi
import io.timemates.api.timers.TimersServiceApi
import io.timemates.api.users.UsersServiceApi
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.exceptions.AlreadyExistsException
import io.timemates.sdk.common.exceptions.InternalServerError
import io.timemates.sdk.common.exceptions.InvalidArgumentException
import io.timemates.sdk.common.exceptions.NotFoundException
import io.timemates.sdk.common.exceptions.PermissionDeniedException
import io.timemates.sdk.common.exceptions.TooManyRequestsException
import io.timemates.sdk.common.exceptions.UnauthorizedException
import io.timemates.sdk.common.exceptions.UnavailableException
import io.timemates.sdk.common.exceptions.UnsupportedException
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.protobuf.ProtoBuf
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Creates an RSocket-based TimeMates request engine.
 *
 * This function configures and creates an RSocket-based TimeMates request engine
 * that communicates with the TimeMates API over WebSockets. You can customize the
 * WebSocket endpoint, coroutine scope, and HTTP client configuration using the
 * provided parameters.
 *
 * **Note**: You should implement ktor client engine to use it.
 *
 * @param endpoint The WebSocket endpoint URL for the TimeMates API.
 * @param coroutineScope The coroutine scope used for RSocket lifecycle.
 * @param config Configuration lambda for customizing the underlying HTTP client.
 * @return An instance of the RSocketTimeMatesRequestsEngine.
 */
public fun RSocketTimeMatesRequestsEngine(
    endpoint: String = "wss://api.timemates.io/v0/rsocket",
    coroutineScope: CoroutineScope,
    config: HttpClientConfig<*>.() -> Unit = {},
): RSocketTimeMatesRequestsEngine {
    val client = HttpClient {
        install(WebSockets)
        install(RSocketSupport) {
            connector {
                maxFragmentSize = 1024

                connectionConfig {
                    keepAlive = KeepAlive(
                        interval = 30.seconds,
                        maxLifetime = 2.minutes
                    )

                    payloadMimeType = PayloadMimeType(
                        data = WellKnownMimeType.ApplicationProtoBuf,
                        metadata = WellKnownMimeType.ApplicationProtoBuf,
                    )
                }
            }
        }
        config()
    }

    return RSocketTimeMatesRequestsEngine(
        client, endpoint, coroutineScope,
    )
}

/**
 * Represents an engine for making requests using RSocket.
 *
 * @property client An instance of [HttpClient] for making HTTP requests.
 * @param endpoint The RSocket endpoint URL. Defaults to "wss://api.timemates.io/v0/rsocket".
 * @param coroutineScope A [CoroutineScope] tied to the RSocket lifecycle.
 */
public class RSocketTimeMatesRequestsEngine internal constructor(
    private val client: HttpClient,
    endpoint: String = "wss://api.timemates.io/rsocket",
    coroutineScope: CoroutineScope,
) : TimeMatesRequestsEngine {
    public companion object {
        public const val API_VERSION: Double = 1.0
    }

    private val rSocket = coroutineScope.async(start = CoroutineStart.LAZY) {
        client.rSocket(endpoint)
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val container = coroutineScope.async(start = CoroutineStart.LAZY) {
        val rSocket = rSocket.await()

        ApiContainer(
            auth = AuthorizationServiceApi(rSocket, ProtoBuf),
            users = UsersServiceApi(rSocket, ProtoBuf),
            timers = TimersServiceApi(rSocket, ProtoBuf),
            timerSessions = TimerSessionsServiceApi(rSocket, ProtoBuf)
        )
    }

    override suspend fun <T : TimeMatesEntity> execute(
        request: TimeMatesRequest<T>,
    ): Result<T> = runCatching {
        return@runCatching rSocketCommandsRegistry.execute(container.await(), request)
            ?: throw UnsupportedException("This type of request is not supported in RSocket engine.")
    }.mapException {
        val message = it.message ?: "No message provided."

        when (it) {
            is RSocketError.Custom -> {
                when (it.errorCode) {
                    HttpStatusCode.TooManyRequests.value -> TooManyRequestsException(message, it)
                    HttpStatusCode.NotFound.value -> NotFoundException(message, it)
                    HttpStatusCode.Conflict.value -> AlreadyExistsException(message, it)
                    HttpStatusCode.Forbidden.value -> PermissionDeniedException(message, it)
                    HttpStatusCode.Unauthorized.value -> UnauthorizedException(message, it)
                    HttpStatusCode.InsufficientStorage.value, HttpStatusCode.GatewayTimeout.value,
                        HttpStatusCode.ServiceUnavailable.value -> UnavailableException(message, it)
                    HttpStatusCode.NotImplemented.value -> UnsupportedException(message, it)
                    else -> InternalServerError(message, it)
                }
            }

            is RSocketError.Invalid -> {
                InvalidArgumentException(message, it)
            }

            else -> {
                InternalServerError(message, it)
            }
        }
    }
}