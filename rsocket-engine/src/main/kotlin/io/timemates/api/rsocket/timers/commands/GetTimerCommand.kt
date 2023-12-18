package io.timemates.api.rsocket.timers.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.timers.sdk
import io.timemates.sdk.timers.requests.GetTimerRequest
import io.timemates.api.timers.requests.GetTimerRequest as RSGetTimerRequest

internal object GetTimerCommand : RSocketCommand<GetTimerRequest, GetTimerRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: GetTimerRequest): GetTimerRequest.Result {
        return apis.timers.getTimer(
            message = RSGetTimerRequest(
                timerId = input.timerId.long,
            ),
            extra = input.accessHash.toExtra(),
        ).let { result ->
            GetTimerRequest.Result(result.sdk())
        }
    }
}