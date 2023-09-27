package io.timemates.api.rsocket.timers.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.timers.types.SerializableTimerSettingsPatch
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketEditTimerRequest(
    val timerId: Long,
    val name: String? = null,
    val description: String? = null,
    val settings: SerializableTimerSettingsPatch? = null,
) : RSocketRequest<Unit>