package io.timemates.api.rsocket.timers.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.timers.rs
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.api.timers.requests.CreateTimerRequest as RSCreateTimerRequest

internal object CreateTimerCommand : RSocketCommand<CreateTimerRequest, CreateTimerRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: CreateTimerRequest): CreateTimerRequest.Result {
        return apis.timers.createTimer(
            message = RSCreateTimerRequest(
                name = input.name.string,
                description = input.description.string,
                settings = input.settings.rs(),
            ),
            extra = input.accessHash.toExtra(),
        ).let { result ->
            CreateTimerRequest.Result(TimerId.createOrThrow(result.timerId))
        }
    }
}