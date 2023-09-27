package io.timemates.sdk.files.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.files.types.FileType
import io.timemates.sdk.files.types.value.FileId
import io.timemates.sdk.files.types.value.FileName
import kotlinx.coroutines.flow.Flow

public data class UploadFileRequest(
    override val accessHash: AccessHash,
    val bytes: Flow<ByteArray>,
    val fileName: FileName,
    val fileType: FileType,
) : AuthorizedTimeMatesRequest<UploadFileRequest.Result>() {
    public data class Result(val fileId: FileId) : TimeMatesEntity()
}