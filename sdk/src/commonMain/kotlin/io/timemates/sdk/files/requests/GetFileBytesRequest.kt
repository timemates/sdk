package io.timemates.sdk.files.requests

import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.files.types.FileType
import io.timemates.sdk.files.types.value.FileId
import kotlinx.coroutines.flow.Flow

public data class GetFileBytesRequest(
    val fileId: FileId,
) : TimeMatesRequest<GetFileBytesRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<GetFileBytesRequest>

    public data class Result(
        val fileType: FileType,
        val bytes: Flow<ByteArray>
    ) : TimeMatesEntity()

    override val requestKey: Key = Key
}