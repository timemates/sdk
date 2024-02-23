package org.timemates.api.rsocket.timers.sessions.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.timers.sessions.requests.StartTimerRequest
import org.timemates.api.timers.sessions.requests.StartTimerRequest as RSStartTimerRequest

internal object StartSessionCommand : RSocketCommand<StartTimerRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: StartTimerRequest): Empty {
        return apis.timerSessions.startTimer(
            message = RSStartTimerRequest {
                timerId = input.timerId.long
            },
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}