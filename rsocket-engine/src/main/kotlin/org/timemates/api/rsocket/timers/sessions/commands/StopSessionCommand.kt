package org.timemates.api.rsocket.timers.sessions.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.timers.sessions.requests.StopTimerRequest
import org.timemates.api.timers.sessions.requests.StopTimerRequest as RSStopTimerRequest

internal object StopSessionCommand : RSocketCommand<StopTimerRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: StopTimerRequest): Empty {
        return apis.timerSessions.stopTimer(
            message = RSStopTimerRequest {
                timerId = input.timerId.long
            },
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}