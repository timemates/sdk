package io.timemates.api.grpc.mappers

import io.timemates.api.timers.members.invites.types.InviteOuterClass.Invite
import io.timemates.api.timers.types.TimerKt
import io.timemates.api.timers.types.TimerOuterClass.Timer
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import io.timemates.sdk.timers.types.value.TimerDescription
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.timers.types.value.TimerName
import io.timemates.sdk.users.profile.types.value.UserId
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import io.timemates.sdk.timers.members.invites.types.Invite as SdkInvite
import io.timemates.sdk.timers.types.Timer as SdkTimer
import io.timemates.sdk.timers.types.TimerSettings as SdkTimerSettings

internal class TimersMapper {
    fun grpcTimerToSdkTimer(timer: Timer): SdkTimer = with(timer) {
        return SdkTimer(
            timerId = TimerId.createOrThrow(id),
            name = TimerName.createOrThrow(name),
            description = TimerDescription.createOrThrow(description),
            ownerId = UserId.createOrThrow(ownerId),
            membersCount = Count.createOrThrow(membersCount),
            settings = grpcSettingsToSdkSettings(settings),
        )
    }

    fun grpcSettingsToSdkSettings(settings: Timer.Settings): SdkTimerSettings = with(settings) {
        return SdkTimerSettings(
            workTime = workTime.seconds,
            restTime = restTime.seconds,
            bigRestTime = bigRestTime.seconds,
            bigRestEnabled = bigRestEnabled,
            bigRestPer = Count.createOrThrow(bigRestPer),
            isEveryoneCanPause = isEveryoneCanPause,
            isConfirmationRequired = isConfirmationRequired,
        )
    }
    fun sdkTimerToGrpcTimer(sdkTimer: SdkTimer): Timer = with(sdkTimer) {
        return Timer.newBuilder()
            .setId(timerId.long)
            .setName(name.string)
            .setDescription(description.string)
            .setOwnerId(ownerId.long)
            .setMembersCount(membersCount.int)
            .setSettings(sdkSettingsToGrpcSettings(settings))
            .build()
    }

    fun sdkSettingsToGrpcSettings(sdkSettings: SdkTimerSettings): Timer.Settings = with(sdkSettings) {
        return Timer.Settings.newBuilder()
            .setWorkTime(workTime.toInt(DurationUnit.MINUTES))
            .setRestTime(restTime.toInt(DurationUnit.MINUTES))
            .setBigRestTime(bigRestTime.toInt(DurationUnit.MINUTES))
            .setBigRestEnabled(bigRestEnabled)
            .setBigRestPer(bigRestPer.int)
            .setIsEveryoneCanPause(isEveryoneCanPause)
            .setIsConfirmationRequired(isConfirmationRequired)
            .build()
    }

    fun sdkSettingsPatchToGrpcSettingsPatch(
        sdkSettings: SdkTimerSettings.Patch
    ): Timer.Settings.Patch = TimerKt.SettingsKt.patch {
        sdkSettings.workTime?.let { workTime = it.toInt(DurationUnit.MINUTES) }
        sdkSettings.restTime?.let { restTime = it.toInt(DurationUnit.MINUTES) }
        sdkSettings.bigRestTime?.let { bigRestTime = it.toInt(DurationUnit.MINUTES) }
        sdkSettings.bigRestPer?.let { bigRestPer = it.int }
        sdkSettings.bigRestEnabled?.let { bigRestEnabled = it }
        sdkSettings.isConfirmationRequired?.let { isConfirmationRequired = it }
        sdkSettings.isEveryoneCanPause?.let { isEveryoneCanPause = it }
    }

    fun grpcInviteToSdkInvite(invite: Invite): SdkInvite {
        return SdkInvite(
            inviteCode = InviteCode.createOrThrow(invite.code),
            creationTime = Instant.fromEpochMilliseconds(invite.creationTime),
            limit = Count.createOrThrow(invite.limit)
        )
    }
}
