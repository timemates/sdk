package org.timemates.api.rsocket.timers.sessions.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.timers.sessions.requests.JoinTimerSessionRequest
import org.timemates.api.timers.sessions.requests.JoinTimerSessionRequest as RSJoinTimerSessionRequest

internal object JoinSessionCommand : RSocketCommand<JoinTimerSessionRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: JoinTimerSessionRequest): Empty {
        return apis.timerSessions.joinSession(
            message = RSJoinTimerSessionRequest {
                timerId = input.timerId.long
            },
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}