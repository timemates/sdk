package io.timemates.sdk.timers.types

import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.timers.types.value.TimerDescription
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.timers.types.value.TimerName
import io.timemates.sdk.users.profile.types.value.UserId

public data class Timer (
    val timerId: TimerId,
    val name: TimerName,
    val description: TimerDescription,
    val ownerId: UserId,
    val membersCount: Count,
) : TimeMatesEntity()