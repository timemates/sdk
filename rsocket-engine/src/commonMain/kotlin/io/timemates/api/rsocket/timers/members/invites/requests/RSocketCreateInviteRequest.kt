package io.timemates.api.rsocket.timers.members.invites.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketCreateInviteRequest(
    val timerId: Long,
    val maxJoiners: Int,
) : RSocketRequest<RSocketCreateInviteRequest.Result> {
    @Serializable
    data class Result(val inviteCode: String)
}