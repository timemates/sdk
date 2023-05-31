package io.timemates.sdk.files.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.files.types.FileType
import io.timemates.sdk.files.types.value.FileId
import kotlinx.coroutines.flow.Flow

public data class UploadFileRequest(
    val accessHash: AccessHash,
    val bytes: Flow<ByteArray>,
    val fileType: FileType,
) : TimeMatesRequest<UploadFileRequest.Result>() {
    public data class Result(val fileId: FileId) : TimeMatesEntity()
}