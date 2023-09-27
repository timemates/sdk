package io.timemates.api.rsocket.authorizations.requests

import io.timemates.api.rsocket.authorizations.types.SerializableAuthorization
import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class ConfigureAccountRequest(
    val verificationHash: String,
    val name: String,
    val description: String?,
) : RSocketRequest<ConfigureAccountRequest.Result> {
    @Serializable
    data class Result(
        val authorization: SerializableAuthorization,
    )
}