package io.timemates.api.rsocket.authorizations.requests

import io.timemates.api.rsocket.authorizations.types.SerializableAuthorization
import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketConfigureAccountRequest(
    val verificationHash: String,
    val name: String,
    val description: String?,
) : RSocketRequest<RSocketConfigureAccountRequest.Result> {
    @Serializable
    data class Result(
        val authorization: SerializableAuthorization,
    )
}