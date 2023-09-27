package io.timemates.api.rsocket.common.serialization

import io.ktor.utils.io.core.ByteReadPacket
import io.ktor.utils.io.streams.inputStream
import io.rsocket.kotlin.payload.Payload
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

private val json = Json {
    ignoreUnknownKeys = true
}

internal fun <T> T.encodeToJson(serializationStrategy: SerializationStrategy<T>): ByteReadPacket {
    return ByteReadPacket(json.encodeToString(serializationStrategy, this).encodeToByteArray())
}

internal inline fun <reified T> ByteArray.decodeFromJson(): T {
    return json.decodeFromString(String(this))
}

@OptIn(ExperimentalSerializationApi::class)
internal fun <T> Payload.decodeFromJson(deserializationStrategy: DeserializationStrategy<T>): T {
    return json.decodeFromStream(deserializationStrategy, data.inputStream())
}