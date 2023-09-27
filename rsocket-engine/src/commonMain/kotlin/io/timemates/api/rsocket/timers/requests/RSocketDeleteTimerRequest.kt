package io.timemates.api.rsocket.timers.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketDeleteTimerRequest(
    val timerId: Long,
) : RSocketRequest<Unit>