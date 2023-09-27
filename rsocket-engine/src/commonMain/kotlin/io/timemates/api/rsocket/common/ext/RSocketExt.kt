package io.timemates.api.rsocket.common.ext

import io.rsocket.kotlin.ExperimentalMetadataApi
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.metadata.RoutingMetadata
import io.rsocket.kotlin.metadata.compositeMetadata
import io.rsocket.kotlin.payload.Payload
import io.rsocket.kotlin.payload.buildPayload
import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.common.metadata.AuthorizationMetadata
import io.timemates.api.rsocket.common.serialization.decodeFromJson
import io.timemates.api.rsocket.common.serialization.encodeToJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.serializer

internal suspend inline fun <reified T : RSocketRequest<R>, reified R> RSocket.requestResponse(
    route: String,
    data: T,
    accessHash: String? = null,
): R = requestResponse(route, serializer<T>(), serializer<R>(), data, accessHash)

internal suspend inline fun <reified T : RSocketRequest<*>> RSocket.fireAndForget(
    route: String,
    data: T,
    accessHash: String? = null,
): Unit = fireAndForget(route, serializer<T>(), data, accessHash)

internal inline fun <reified T : RSocketRequest<R>, reified R> RSocket.requestStream(
    route: String,
    data: T,
    accessHash: String? = null,
): Flow<R> = requestStream(route, serializer<T>(), serializer<R>(), data, accessHash)

internal suspend fun <T : Any, R> RSocket.requestResponse(
    route: String,
    dataSerStrategy: SerializationStrategy<T>,
    resultDeSerStrategy: DeserializationStrategy<R>,
    data: T,
    accessHash: String? = null,
): R {
    val payload = createPayload(route, data, dataSerStrategy, accessHash)
    return requestResponse(payload).decodeFromJson(resultDeSerStrategy)
}

internal fun <T : Any, R> RSocket.requestStream(
    route: String,
    dataSerStrategy: SerializationStrategy<T>,
    resultDeSerStrategy: DeserializationStrategy<R>,
    data: T,
    accessHash: String? = null,
): Flow<R> {
    val payload = createPayload(route, data, dataSerStrategy, accessHash)
    return requestStream(payload).map { it.decodeFromJson(resultDeSerStrategy) }
}

internal suspend fun <T : Any> RSocket.fireAndForget(
    route: String,
    dataSerStrategy: SerializationStrategy<T>,
    data: T,
    accessHash: String? = null,
) {
    val payload = createPayload(route, data, dataSerStrategy, accessHash)
    return fireAndForget(payload)
}

@OptIn(ExperimentalMetadataApi::class)
private fun <T> createPayload(
    route: String,
    data: T,
    dataSerStrategy: SerializationStrategy<T>,
    accessHash: String?,
): Payload {
    return buildPayload {
        compositeMetadata {
            add(RoutingMetadata(route))
            if (accessHash != null)
                add(AuthorizationMetadata(accessHash))
        }
        data(data.encodeToJson(dataSerStrategy))
    }
}