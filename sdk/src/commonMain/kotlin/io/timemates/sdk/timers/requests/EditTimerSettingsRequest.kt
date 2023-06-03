package io.timemates.sdk.timers.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.TimerSettings

public data class EditTimerSettingsRequest(
    val accessHash: AccessHash,
    val patch: TimerSettings.Patch,
) : TimeMatesRequest<Empty>()