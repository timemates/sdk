package io.timemates.api.rsocket

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.websocket.WebSockets
import io.rsocket.kotlin.ExperimentalMetadataApi
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.core.WellKnownMimeType
import io.rsocket.kotlin.keepalive.KeepAlive
import io.rsocket.kotlin.ktor.client.RSocketSupport
import io.rsocket.kotlin.ktor.client.rSocket
import io.rsocket.kotlin.metadata.RoutingMetadata
import io.rsocket.kotlin.metadata.compositeMetadata
import io.rsocket.kotlin.payload.PayloadMimeType
import io.rsocket.kotlin.payload.buildPayload
import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.common.metadata.AuthorizationMetadata
import io.timemates.api.rsocket.common.serialization.decodeFromJson
import io.timemates.api.rsocket.common.serialization.encodeToJson
import io.timemates.api.rsocket.timers.types.sdk
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.exceptions.UnsupportedException
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.requests.EditTimerRequest
import io.timemates.sdk.timers.requests.GetTimerRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.serializer
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import io.timemates.api.rsocket.timers.requests.RSocketEditTimerRequest as RSocketEditTimerRequest
import io.timemates.api.rsocket.timers.requests.RSocketGetTimerRequest as RSocketGetTimerRequest

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
        install(RSocketSupport) {
            connector {
                maxFragmentSize = 1024

                connectionConfig {
                    keepAlive = KeepAlive(
                        interval = 30.seconds,
                        maxLifetime = 2.minutes
                    )

                    payloadMimeType = PayloadMimeType(
                        data = WellKnownMimeType.ApplicationJson,
                        metadata = WellKnownMimeType.MessageRSocketCompositeMetadata,
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
    endpoint: String = "wss://api.timemates.io/v0/rsocket",
    coroutineScope: CoroutineScope,
) : TimeMatesRequestsEngine {
    public companion object {
        public const val API_VERSION: Int = 1
    }

    private val rSocket = coroutineScope.async(start = CoroutineStart.LAZY) {
        client.rSocket(endpoint)
    }

    override suspend fun <T : TimeMatesEntity> execute(
        request: TimeMatesRequest<T>,
    ): Result<T> = runCatching {
        val rSocket = rSocket.await()
        return@runCatching rSocketCommandsRegistry.execute(rSocket, request)
            ?: throw UnsupportedException("This type of request is not supported in RSocket engine.")
    }
}