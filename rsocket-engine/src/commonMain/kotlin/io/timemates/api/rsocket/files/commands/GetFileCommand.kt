package io.timemates.api.rsocket.files.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestStream
import io.timemates.api.rsocket.common.serialization.decodeFromJson
import io.timemates.api.rsocket.files.requests.RSocketGetFileRequest
import io.timemates.api.rsocket.files.types.sdk
import io.timemates.sdk.files.requests.GetFileBytesRequest
import kotlinx.coroutines.flow.first

internal object GetFileCommand : RSocketCommand<GetFileBytesRequest, GetFileBytesRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: GetFileBytesRequest): GetFileBytesRequest.Result {
        return rSocket.requestStream(
            route = "files.get",
            data = RSocketGetFileRequest(input.fileId.string),
        ).let { result ->
            val metadata = result.first().decodeFromJson<RSocketGetFileRequest.Response.Metadata>()
            GetFileBytesRequest.Result(metadata.fileType.sdk(), result)
        }
    }
}