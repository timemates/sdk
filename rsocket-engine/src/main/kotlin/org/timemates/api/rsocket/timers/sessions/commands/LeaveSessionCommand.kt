package org.timemates.api.rsocket.timers.sessions.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.timers.sessions.requests.LeaveTimerSessionRequest
import com.google.protobuf.Empty.Companion as RSEmpty

internal object LeaveSessionCommand : RSocketCommand<LeaveTimerSessionRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: LeaveTimerSessionRequest): Empty {
        return apis.timerSessions.leaveSession(
            message = RSEmpty.Default,
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}