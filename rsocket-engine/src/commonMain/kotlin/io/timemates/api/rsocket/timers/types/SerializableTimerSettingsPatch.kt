package io.timemates.api.rsocket.timers.types

import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
internal data class SerializableTimerSettingsPatch(
    val workTime: Duration? = null,
    val restTime: Duration? = null,
    val bigRestTime: Duration? = null,
    val bigRestEnabled: Boolean? = null,
    val bigRestPer: Int? = null,
    val isEveryoneCanPause: Boolean? = null,
    val isConfirmationRequired: Boolean? = null,
)