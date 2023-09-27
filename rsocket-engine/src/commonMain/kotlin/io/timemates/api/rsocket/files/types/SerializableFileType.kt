package io.timemates.api.rsocket.files.types

import io.timemates.sdk.files.types.FileType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class SerializableFileType {
    @SerialName("image")
    IMAGE,
}

internal fun SerializableFileType.sdk(): FileType = when (this) {
    SerializableFileType.IMAGE -> FileType.IMAGE
}

internal fun FileType.serializable(): SerializableFileType = when (this) {
    FileType.IMAGE -> SerializableFileType.IMAGE
}