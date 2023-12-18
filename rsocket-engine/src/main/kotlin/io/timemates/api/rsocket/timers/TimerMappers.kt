package io.timemates.api.rsocket.timers

import io.timemates.api.timers.sessions.types.TimerState
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import io.timemates.sdk.timers.types.Timer
import io.timemates.sdk.timers.types.TimerSettings
import io.timemates.sdk.timers.types.value.TimerDescription
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.timers.types.value.TimerName
import io.timemates.sdk.users.profile.types.value.UserId
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit
import io.timemates.api.timers.members.invites.types.Invite as RSInvite
import io.timemates.api.timers.sessions.types.TimerState as RSTimerState
import io.timemates.api.timers.types.Timer as RSTimer
import io.timemates.sdk.timers.members.invites.types.Invite as SdkInvite
import io.timemates.sdk.timers.types.Timer as SdkTimer
import io.timemates.sdk.timers.types.Timer.State as SdkTimerState
import io.timemates.sdk.timers.types.TimerSettings as SdkTimerSettings

internal fun RSTimer.sdk(): SdkTimer {
    return SdkTimer(
        timerId = TimerId.createOrThrow(id),
        name = TimerName.createOrThrow(name),
        description = TimerDescription.createOrThrow(description),
        ownerId = UserId.createOrThrow(ownerId),
        membersCount = Count.createOrThrow(membersCount),
        state = currentState?.sdk() ?: SdkTimerState.Inactive(Instant.DISTANT_PAST),
        settings = settings?.sdk() ?: TimerSettings(),
    )
}

internal fun RSTimerState.sdk(): SdkTimerState {
    val publishTime = Instant.fromEpochMilliseconds(publishTime)
    return when (phase) {
        is TimerState.PhaseOneOf.ConfirmationWaiting -> SdkTimerState.ConfirmationWaiting(
            endsAt = Instant.fromEpochMilliseconds(phase.value!!.endsAt),
            publishTime = publishTime,
        )
        is TimerState.PhaseOneOf.Inactive -> SdkTimerState.Inactive(
            publishTime = publishTime,
        )
        is TimerState.PhaseOneOf.Paused -> SdkTimerState.Paused(
            publishTime = publishTime,
        )
        is TimerState.PhaseOneOf.Rest -> SdkTimerState.Rest(
            endsAt = Instant.fromEpochMilliseconds(phase.value!!.endsAt),
            publishTime = publishTime,
        )
        is TimerState.PhaseOneOf.Running -> SdkTimerState.Running(
            endsAt = Instant.fromEpochMilliseconds(phase.value!!.endsAt),
            publishTime = publishTime,
        )
        null -> SdkTimerState.Inactive(Instant.DISTANT_PAST)
    }
}

internal fun RSTimer.Settings.sdk(): SdkTimerSettings {
    return SdkTimerSettings(
        workTime = workTime.minutes,
        restTime = restTime.minutes,
        bigRestTime = bigRestTime.minutes,
        bigRestEnabled = bigRestEnabled,
        bigRestPer = Count.createOrThrow(bigRestPer),
        isEveryoneCanPause = isEveryoneCanPause,
        isConfirmationRequired = isConfirmationRequired,
    )
}

internal fun SdkTimerSettings.rs(): RSTimer.Settings {
    return RSTimer.Settings(
        workTime = workTime.toInt(DurationUnit.MINUTES),
        restTime = restTime.toInt(DurationUnit.MINUTES),
        bigRestEnabled = bigRestEnabled,
        bigRestTime = bigRestTime.toInt(DurationUnit.MINUTES),
        isConfirmationRequired = isConfirmationRequired,
    )
}

internal fun RSInvite.sdk(): SdkInvite {
    return SdkInvite(
        inviteCode = InviteCode.createOrThrow(code),
        creationTime = Instant.fromEpochMilliseconds(creationTime),
        limit = Count.createOrThrow(limit),
    )
}