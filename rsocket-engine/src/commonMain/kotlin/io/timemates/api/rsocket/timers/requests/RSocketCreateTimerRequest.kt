package io.timemates.api.rsocket.timers.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.timers.types.SerializableTimerSettings
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketCreateTimerRequest(
    val name: String,
    val description: String = "",
    val settings: SerializableTimerSettings? = null,
) : RSocketRequest<RSocketCreateTimerRequest.Result> {
    @Serializable
    data class Result(val timerId: Long)
}