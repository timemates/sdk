package io.timemates.api.rsocket.timers.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.timers.types.SerializableTimer
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketGetTimerRequest(
    val timerId: Long,
) : RSocketRequest<RSocketGetTimerRequest.Result> {
    @Serializable
    data class Result(val timer: SerializableTimer)
}