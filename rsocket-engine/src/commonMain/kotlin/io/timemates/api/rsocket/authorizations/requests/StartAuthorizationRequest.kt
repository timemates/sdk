package io.timemates.api.rsocket.authorizations.requests

import io.timemates.api.rsocket.authorizations.types.SerializableAuthMetadata
import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class StartAuthorizationRequest(
    val email: String,
    val clientMetadata: SerializableAuthMetadata,
) : RSocketRequest<StartAuthorizationRequest.Result> {
    @Serializable
    data class Result(
        val verificationHash: String,
        val expiresAt: Long,
        val attempts: Int,
    )
}