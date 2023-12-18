package io.timemates.api.rsocket.timers.sessions.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.StopTimerRequest
import io.timemates.api.timers.sessions.requests.StopTimerRequest as RSStopTimerRequest

internal object StopSessionCommand : RSocketCommand<StopTimerRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: StopTimerRequest): Empty {
        return apis.timerSessions.stopTimer(
            message = RSStopTimerRequest(
                timerId = input.timerId.long,
            ),
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}