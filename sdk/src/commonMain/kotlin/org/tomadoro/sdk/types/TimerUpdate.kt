package org.tomadoro.sdk.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.value.DateTime

public sealed interface TimerUpdate {
    @SerialName("session_timer_confirmation")
    @Serializable
    public object Confirmation : TimerUpdate

    @SerialName("timer_settings_update")
    @Serializable
    public class Settings(
        @SerialName("new_settings") public val patch: TimerSettings.Patch
    ) : TimerUpdate

    @SerialName("timer_started")
    @Serializable
    public class TimerStarted(@SerialName("ends_at") public val endsAt: DateTime) : TimerUpdate

    @SerialName("timer_stopped")
    @Serializable
    public class TimerStopped(@SerialName("starts_at") public val startsAt: DateTime?) : TimerUpdate

    @SerialName("session_failed")
    @Serializable
    public object SessionFailed : TimerUpdate

    @SerialName("session_finished")
    @Serializable
    public object SessionFinished : TimerUpdate
}