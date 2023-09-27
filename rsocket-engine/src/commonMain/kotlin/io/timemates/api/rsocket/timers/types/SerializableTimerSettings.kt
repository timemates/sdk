package io.timemates.api.rsocket.timers.types

import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.timers.types.TimerSettings
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
internal data class SerializableTimerSettings(
    val workTime: Duration,
    val restTime: Duration,
    val bigRestTime: Duration,
    val bigRestEnabled: Boolean,
    val bigRestPer: Int,
    val isEveryoneCanPause: Boolean,
    val isConfirmationRequired: Boolean,
)

internal fun TimerSettings.serializable(): SerializableTimerSettings {
    return SerializableTimerSettings(
        workTime = workTime,
        restTime = restTime,
        bigRestTime = bigRestTime,
        bigRestEnabled = bigRestEnabled,
        bigRestPer = bigRestPer.int,
        isEveryoneCanPause = isEveryoneCanPause,
        isConfirmationRequired = isConfirmationRequired,
    )
}

internal fun SerializableTimerSettings.sdk(): TimerSettings {
    return TimerSettings(
        workTime = workTime,
        restTime = restTime,
        bigRestTime = bigRestTime,
        bigRestEnabled = bigRestEnabled,
        bigRestPer = Count.createOrThrow(bigRestPer),
        isEveryoneCanPause = isEveryoneCanPause,
        isConfirmationRequired = isConfirmationRequired,
    )
}