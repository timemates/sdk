package io.timemates.api.rsocket.timers.sessions.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.timers.sdk
import io.timemates.sdk.timers.sessions.requests.GetTimerStateRequest
import kotlinx.coroutines.flow.map
import io.timemates.api.timers.sessions.requests.GetTimerStateRequest as RSGetTimerStateRequest

internal object GetTimerStateCommand : RSocketCommand<GetTimerStateRequest, GetTimerStateRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: GetTimerStateRequest): GetTimerStateRequest.Result {
        return apis.timerSessions.getState(
            message = RSGetTimerStateRequest(timerId = input.timerId.long),
            extra = input.accessHash.toExtra(),
        ).let { result ->
            GetTimerStateRequest.Result(result.map { it.sdk() })
        }
    }
}