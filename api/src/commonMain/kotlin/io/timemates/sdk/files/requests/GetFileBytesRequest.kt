package io.timemates.sdk.files.requests

import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.files.types.value.FileId
import kotlinx.coroutines.flow.Flow

public data class GetFileBytesRequest(
    val fileId: FileId,
) : TimeMatesRequest<GetFileBytesRequest.Result>() {
    public data class Result(
        val bytes: Flow<ByteArray>
    ) : TimeMatesEntity()
}