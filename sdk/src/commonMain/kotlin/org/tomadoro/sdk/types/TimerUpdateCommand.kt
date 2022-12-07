package org.tomadoro.sdk.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface TimerSessionCommand {
    @SerialName("timer_start")
    @Serializable
    public object StartTimer : TimerSessionCommand

    @SerialName("timer_stop")
    @Serializable
    public object StopTimer : TimerSessionCommand

    @SerialName("session_timer_confirm")
    @Serializable
    public object ConfirmAttendance : TimerSessionCommand

    @SerialName("session_leave")
    @Serializable
    public object LeaveSession : TimerSessionCommand
}