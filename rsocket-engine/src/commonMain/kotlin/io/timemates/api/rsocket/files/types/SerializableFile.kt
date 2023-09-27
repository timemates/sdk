package io.timemates.api.rsocket.files.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed class SerializableFile {
    abstract val fileId: String

    /**
     * File with image.
     */
    @SerialName("image")
    data class Image(override val fileId: String) : SerializableFile()
}