package org.timemates.api.rsocket.timers.sessions.commands

import org.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import org.timemates.sdk.timers.sessions.requests.ConfirmTimerRoundRequest
import org.timemates.sdk.timers.sessions.requests.GetTimerStateRequest
import org.timemates.sdk.timers.sessions.requests.GetUserCurrentSessionRequest
import org.timemates.sdk.timers.sessions.requests.JoinTimerSessionRequest
import org.timemates.sdk.timers.sessions.requests.LeaveTimerSessionRequest
import org.timemates.sdk.timers.sessions.requests.PingSessionRequest
import org.timemates.sdk.timers.sessions.requests.StartTimerRequest
import org.timemates.sdk.timers.sessions.requests.StopTimerRequest

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