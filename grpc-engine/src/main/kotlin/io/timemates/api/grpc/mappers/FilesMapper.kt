package io.timemates.api.grpc.mappers

import io.timemates.api.files.requests.UploadFileRequestOuterClass
import io.timemates.sdk.files.types.FileType
import io.timemates.api.files.requests.UploadFileRequestOuterClass.UploadFileRequest.FileMetadata.FileType as GrpcFileType

internal class FilesMapper {
    fun sdkFileTypeToGrpcFileType(fileType: FileType): GrpcFileType {
        return when (fileType) {
            FileType.IMAGE -> GrpcFileType.IMAGE
        }
    }
}