package io.timemates.api.rsocket.files.commands

import io.ktor.utils.io.core.ByteReadPacket
import io.rsocket.kotlin.ExperimentalMetadataApi
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.metadata.RoutingMetadata
import io.rsocket.kotlin.metadata.compositeMetadata
import io.rsocket.kotlin.payload.Payload
import io.rsocket.kotlin.payload.buildPayload
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.serialization.decodeFromJson
import io.timemates.api.rsocket.common.serialization.encodeToJson
import io.timemates.api.rsocket.files.requests.RSocketUploadFileRequest
import io.timemates.api.rsocket.files.types.serializable
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.files.requests.UploadFileRequest
import io.timemates.sdk.files.types.value.FileId
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.serialization.serializer

internal object UploadFileCommand : RSocketCommand<UploadFileRequest, UploadFileRequest.Result> {
    @OptIn(ExperimentalMetadataApi::class)
    override suspend fun execute(rSocket: RSocket, input: UploadFileRequest): UploadFileRequest.Result {
        return rSocket.requestChannel(
            initPayload = buildPayload {
                data(
                    RSocketUploadFileRequest.Metadata(
                        fileName = input.fileName.string,
                        fileType = input.fileType.serializable(),
                    ).encodeToJson(serializer())
                )
                compositeMetadata {
                    add(RoutingMetadata("files.upload"))
                }
            },
            payloads = input.bytes.map { Payload(data = ByteReadPacket(it)) }
        ).let { result ->
            val serialized = result.last().decodeFromJson<RSocketUploadFileRequest.Response>(serializer())
            UploadFileRequest.Result(fileId = FileId.createOrThrow(serialized.fileId))
        }
    }
}