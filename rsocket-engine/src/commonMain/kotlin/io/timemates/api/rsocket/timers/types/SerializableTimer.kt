package io.timemates.api.rsocket.timers.types

import io.timemates.api.rsocket.timers.sessions.types.SerializableTimerState
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.timers.types.Timer
import io.timemates.sdk.timers.types.value.TimerDescription
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.timers.types.value.TimerName
import io.timemates.sdk.users.profile.types.value.UserId
import kotlinx.serialization.Serializable

@Serializable
internal data class SerializableTimer(
    val id: Long,
    val name: String,
    val description: String,
    val ownerId: Long,
    val settings: SerializableTimerSettings,
    val membersCount: Int,
    val state: SerializableTimerState,
)

internal fun SerializableTimer.sdk(): Timer {
    return Timer(
        timerId = TimerId.createOrThrow(id),
        name = TimerName.createOrThrow(name),
        description = TimerDescription.createOrThrow(description),
        ownerId = UserId.createOrThrow(ownerId),
        membersCount = Count.createOrThrow(membersCount),
        settings = settings.sdk(),
    )
}