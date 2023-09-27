package io.timemates.api.rsocket

import io.ktor.client.HttpClient
import io.rsocket.kotlin.ExperimentalMetadataApi
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.ktor.client.rSocket
import io.rsocket.kotlin.metadata.RoutingMetadata
import io.rsocket.kotlin.metadata.compositeMetadata
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
import io.timemates.api.rsocket.timers.requests.RSocketEditTimerRequest as RSocketEditTimerRequest
import io.timemates.api.rsocket.timers.requests.RSocketGetTimerRequest as RSocketGetTimerRequest

/**
 * Represents an engine for making requests using RSocket.
 *
 * @property client An instance of [HttpClient] for making HTTP requests.
 * @param endpoint The RSocket endpoint URL. Defaults to "wss://api.timemates.io/v0/rsocket".
 * @param coroutineScope A [CoroutineScope] tied to the RSocket lifecycle.
 */
public class RSocketTimeMatesRequestsEngine private constructor(
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