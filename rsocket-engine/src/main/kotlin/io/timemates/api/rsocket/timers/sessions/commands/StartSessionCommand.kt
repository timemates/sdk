package io.timemates.api.rsocket.timers.sessions.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.StartTimerRequest
import io.timemates.api.timers.sessions.requests.StartTimerRequest as RSStartTimerRequest

internal object StartSessionCommand : RSocketCommand<StartTimerRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: StartTimerRequest): Empty {
        return apis.timerSessions.startTimer(
            message = RSStartTimerRequest(
                timerId = input.timerId.long,
            ),
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}