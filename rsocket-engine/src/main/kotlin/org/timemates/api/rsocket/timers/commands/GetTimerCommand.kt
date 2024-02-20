package org.timemates.api.rsocket.timers.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.rsocket.timers.sdk
import org.timemates.sdk.timers.requests.GetTimerRequest
import org.timemates.api.timers.requests.GetTimerRequest as RSGetTimerRequest

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