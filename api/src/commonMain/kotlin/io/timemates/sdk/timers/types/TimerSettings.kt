package io.timemates.sdk.timers.types

import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.value.Count
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

public data class TimerSettings(
    val workTime: Duration = 25.minutes,
    val restTime: Duration = 5.minutes,
    val bigRestTime: Duration = 10.minutes,
    val bigRestEnabled: Boolean = true,
    val bigRestPer: Count = Count.createOrThrow(4),
    val isEveryoneCanPause: Boolean = false,
    val isConfirmationRequired: Boolean = false,
) : TimeMatesEntity() {
    public data class Patch(
        val workTime: Duration? = null,
        val restTime: Duration? = null,
        val bigRestTime: Duration? = null,
        val bigRestEnabled: Boolean? = null,
        val bigRestPer: Count? = null,
        val isEveryoneCanPause: Boolean? = null,
        val isConfirmationRequired: Boolean? = null,
    ) : TimeMatesEntity()
}