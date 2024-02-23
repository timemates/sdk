package org.timemates.api.rsocket.timers.sessions.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.rsocket.timers.sdk
import org.timemates.sdk.timers.sessions.requests.GetTimerStateRequest
import kotlinx.coroutines.flow.map
import org.timemates.api.timers.sessions.requests.GetTimerStateRequest as RSGetTimerStateRequest

internal object GetTimerStateCommand : RSocketCommand<GetTimerStateRequest, GetTimerStateRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: GetTimerStateRequest): GetTimerStateRequest.Result {
        return apis.timerSessions.getState(
            message = RSGetTimerStateRequest {
                timerId = input.timerId.long
            },
            extra = input.accessHash.toExtra(),
        ).let { result ->
            GetTimerStateRequest.Result(result.map { it.sdk() })
        }
    }
}