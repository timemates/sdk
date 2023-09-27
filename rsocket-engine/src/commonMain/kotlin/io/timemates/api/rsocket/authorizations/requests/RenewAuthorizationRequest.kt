package io.timemates.api.rsocket.authorizations.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class RenewAuthorizationRequest(
    val refreshHash: String,
) : RSocketRequest<RenewAuthorizationRequest.Result> {
    @Serializable
    data class Result(val accessHash: String)
}