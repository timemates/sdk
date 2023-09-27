package io.timemates.api.rsocket.files.requests

import io.timemates.api.rsocket.files.types.SerializableFileType
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
internal data class UploadFileRequest(
    val fileType: SerializableFileType,
    val bytes: Flow<ByteArray>,
) {
    @Serializable
    data class Metadata(val fileName: String, val fileType: SerializableFileType)

    @Serializable
    data class Response(val fileId: String)
}