package io.timemates.api.rsocket.files.requests

import io.timemates.api.rsocket.files.types.SerializableFileType
import kotlinx.serialization.Serializable

@Serializable
internal data class GetFileRequest(
    val fileId: String,
) {
    @Serializable
    sealed interface Response {
        data class Metadata(
            val fileType: SerializableFileType,
        ) : Response

        @JvmInline
        value class Chunk(val bytes: ByteArray) : Response
    }
}