package io.timemates.api.rsocket.timers.sessions.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.JoinTimerSessionRequest
import io.timemates.api.timers.sessions.requests.JoinTimerSessionRequest as RSJoinTimerSessionRequest

internal object JoinSessionCommand : RSocketCommand<JoinTimerSessionRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: JoinTimerSessionRequest): Empty {
        return apis.timerSessions.joinSession(
            message = RSJoinTimerSessionRequest(
                timerId = input.timerId.long,
            ),
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}