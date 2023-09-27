package io.timemates.api.rsocket.timers.members.invites.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketRemoveInviteRequest(
    val timerId: Long,
    val code: String,
) : RSocketRequest<Unit>