package io.timemates.sdk.timers.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.TimerSettings
import io.timemates.sdk.timers.types.value.TimerDescription
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.timers.types.value.TimerName

public data class EditTimerRequest(
    val accessHash: AccessHash,
    val timerId: TimerId,
    val name: TimerName? = null,
    val description: TimerDescription? = null,
    val settings: TimerSettings.Patch? = null,
) : TimeMatesRequest<Empty>()