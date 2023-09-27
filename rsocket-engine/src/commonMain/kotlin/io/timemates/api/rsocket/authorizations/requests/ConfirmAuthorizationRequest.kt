package io.timemates.api.rsocket.authorizations.requests

import io.timemates.api.rsocket.authorizations.types.SerializableAuthorization
import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class ConfirmAuthorizationRequest(
    val verificationHash: String,
    val confirmationCode: String,
) : RSocketRequest<ConfirmAuthorizationRequest.Response> {
    data class Response(
        val isNewAccount: Boolean,
        val authorization: SerializableAuthorization?,
    )
}