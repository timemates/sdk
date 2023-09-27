package io.timemates.api.rsocket.authorizations.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketRenewAuthorizationRequest(
    val refreshHash: String,
) : RSocketRequest<RSocketRenewAuthorizationRequest.Result> {
    @Serializable
    data class Result(val accessHash: String)
}