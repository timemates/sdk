package io.timemates.api.rsocket.files.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.files.types.SerializableFileType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketGetFileRequest(
    val fileId: String,
) : RSocketRequest<ByteArray> {
    @Serializable
    sealed interface Response {
        @SerialName("metadata")
        data class Metadata(
            val fileType: SerializableFileType,
        ) : Response

        @SerialName("chunk")
        @JvmInline
        value class Chunk(val bytes: ByteArray) : Response
    }
}