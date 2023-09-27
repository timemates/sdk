package io.timemates.api.rsocket.authorizations.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface SerializableAuthorizationScope {
    @SerialName("super")
    data object All : SerializableAuthorizationScope

    @SerialName("auth:read")
    data object AuthRead : SerializableAuthorizationScope

    @SerialName("auth:write")
    data object AuthWrite : SerializableAuthorizationScope

    @SerialName("users:read")
    data object UsersRead : SerializableAuthorizationScope

    @SerialName("users:write")
    data object UsersWrite : SerializableAuthorizationScope

    @SerialName("timers:read")
    data object TimersRead : SerializableAuthorizationScope

    @SerialName("timers:write")
    data object TimersWrite : SerializableAuthorizationScope

    @SerialName("files:read")
    data object FilesRead : SerializableAuthorizationScope

    @SerialName("files:write")
    data object FilesWrite : SerializableAuthorizationScope
}