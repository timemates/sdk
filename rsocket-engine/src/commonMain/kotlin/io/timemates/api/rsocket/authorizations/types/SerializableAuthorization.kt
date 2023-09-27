package io.timemates.api.rsocket.authorizations.types

import io.timemates.sdk.authorization.sessions.types.Authorization
import kotlinx.serialization.Serializable

@Serializable
internal data class SerializableAuthorization(
    val userId: Long,
    val accessHash: String,
    val refreshAccessHash: String,
    val scopes: List<SerializableAuthorizationScope>,
    // todo expiresAt for refresh hash
    val expiresAt: Long,
    val createdAt: Long,
    val clientMetadata: SerializableAuthMetadata,
)

// todo expiresAt for refresh hash
internal fun SerializableAuthorization.sdk(): Authorization = TODO()