package io.timemates.api.rsocket.users.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class EditEmailRequest(
    val email: String,
) : RSocketRequest<Unit>