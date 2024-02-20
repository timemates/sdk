package org.timemates.api.rsocket.timers.commands

import org.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import org.timemates.api.rsocket.timers.members.commands.timerMembers
import org.timemates.api.rsocket.timers.sessions.commands.timerSessions
import org.timemates.sdk.timers.requests.CreateTimerRequest
import org.timemates.sdk.timers.requests.EditTimerRequest
import org.timemates.sdk.timers.requests.GetTimerRequest
import org.timemates.sdk.timers.requests.GetUserTimersRequest
import org.timemates.sdk.timers.requests.RemoveTimerRequest

/**
 * RSocket commands related to timers.
 */
internal fun RSocketCommandsBuilderScope.timers() {
    timerMembers()
    timerSessions()

    CreateTimerCommand associatedWith CreateTimerRequest
    EditTimerCommand associatedWith EditTimerRequest
    GetTimerCommand associatedWith GetTimerRequest
    RemoveTimerCommand associatedWith RemoveTimerRequest
    GetUserTimersCommand associatedWith GetUserTimersRequest
}