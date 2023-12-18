package io.timemates.api.rsocket.timers.sessions.commands

import io.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import io.timemates.sdk.timers.sessions.requests.ConfirmTimerRoundRequest
import io.timemates.sdk.timers.sessions.requests.GetTimerStateRequest
import io.timemates.sdk.timers.sessions.requests.GetUserCurrentSessionRequest
import io.timemates.sdk.timers.sessions.requests.JoinTimerSessionRequest
import io.timemates.sdk.timers.sessions.requests.LeaveTimerSessionRequest
import io.timemates.sdk.timers.sessions.requests.PingSessionRequest
import io.timemates.sdk.timers.sessions.requests.StartTimerRequest
import io.timemates.sdk.timers.sessions.requests.StopTimerRequest

/**
 * RSocket commands related to timer members.
 */
internal fun RSocketCommandsBuilderScope.timerSessions() {
    ConfirmSessionCommand associatedWith ConfirmTimerRoundRequest
    LeaveSessionCommand associatedWith LeaveTimerSessionRequest
    PingSessionCommand associatedWith PingSessionRequest
    GetCurrentSessionCommand associatedWith GetUserCurrentSessionRequest
    StartSessionCommand associatedWith StartTimerRequest
    StopSessionCommand associatedWith StopTimerRequest
    JoinSessionCommand associatedWith JoinTimerSessionRequest
    GetTimerStateCommand associatedWith GetTimerStateRequest
}