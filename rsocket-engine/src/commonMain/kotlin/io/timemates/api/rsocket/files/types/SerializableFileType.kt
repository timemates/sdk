package io.timemates.api.rsocket.files.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class SerializableFileType {
    @SerialName("image")
    IMAGE,
}