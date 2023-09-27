package io.timemates.api.rsocket.authorizations.types

import io.timemates.sdk.authorization.sessions.types.Authorization
import kotlinx.serialization.Serializable

@Serializable
internal data class SerializableAuthMetadata(
    val applicationName: String,
    val clientVersion: String,
    val clientIpAddress: String?,
)

internal fun Authorization.Metadata.serializable(): SerializableAuthMetadata = SerializableAuthMetadata(
    applicationName = applicationName.string,
    clientVersion = clientVersion.string,
    clientIpAddress = clientIpAddress.string,
)