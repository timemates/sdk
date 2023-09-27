package io.timemates.api.rsocket.timers.members.invites.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketJoinTimerByCodeRequest(
    val code: String,
) : RSocketRequest<RSocketJoinTimerByCodeRequest.Result> {
    @Serializable
    data class Result(val timerId: Long)
}