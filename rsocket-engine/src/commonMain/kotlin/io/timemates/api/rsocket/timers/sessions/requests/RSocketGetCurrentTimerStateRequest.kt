package io.timemates.api.rsocket.timers.sessions.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.timers.sessions.types.SerializableTimerState
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketGetCurrentTimerStateRequest(
    val timerId: Long,
) : RSocketRequest<SerializableTimerState>