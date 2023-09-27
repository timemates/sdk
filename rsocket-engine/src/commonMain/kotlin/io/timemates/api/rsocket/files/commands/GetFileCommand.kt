package io.timemates.api.rsocket.files.commands

import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.payload.buildPayload
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.serialization.encodeToJson
import io.timemates.api.rsocket.files.requests.GetFileRequest
import io.timemates.sdk.files.requests.GetFileBytesRequest
import kotlinx.serialization.serializer

internal object GetFileCommand : RSocketCommand<GetFileBytesRequest, GetFileBytesRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: GetFileBytesRequest): GetFileBytesRequest.Result {
        return rSocket.requestResponse(
            buildPayload {
                data(GetFileRequest(input.fileId.string).encodeToJson(serializer()))
            }
        ).let { result ->
//            GetFileBytesRequest.Result(result.data)
            TODO()
        }
    }
}