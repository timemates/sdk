package io.timemates.api.rsocket.timers.commands

import io.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import io.timemates.api.rsocket.timers.members.commands.timerMembers
import io.timemates.api.rsocket.timers.sessions.commands.timerSessions
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.requests.EditTimerRequest
import io.timemates.sdk.timers.requests.GetTimerRequest
import io.timemates.sdk.timers.requests.GetUserTimersRequest
import io.timemates.sdk.timers.requests.RemoveTimerRequest

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