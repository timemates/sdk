package io.timemates.api.rsocket.users.types

import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.users.profile.types.Avatar
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface SerializableAvatar {
    @SerialName("gravatar")
    data class Gravatar(val gravatarId: String) : SerializableAvatar

    @SerialName("timemates")
    data class TimeMates(val fileId: String) : SerializableAvatar
}

internal fun Avatar.serializable(): SerializableAvatar {
    return when (this) {
        is Avatar.GravatarId -> SerializableAvatar.Gravatar(string)
        is Avatar.FileId -> SerializableAvatar.TimeMates(string)
    }
}

internal fun SerializableAvatar.sdk(): Avatar {
    return when (this) {
        is SerializableAvatar.TimeMates -> Avatar.GravatarId.createOrThrow(fileId)
        is SerializableAvatar.Gravatar -> Avatar.FileId.createOrThrow(gravatarId)
    }
}