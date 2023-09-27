package io.timemates.api.rsocket.authorizations.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal sealed class RSocketTerminateAuthorizationRequest<R> : RSocketRequest<R> {
    /**
     * This type of termination request terminates authorization with which
     * user has sent termination request.
     */
    @SerialName("current")
    data object Current : RSocketTerminateAuthorizationRequest<Unit>()
}