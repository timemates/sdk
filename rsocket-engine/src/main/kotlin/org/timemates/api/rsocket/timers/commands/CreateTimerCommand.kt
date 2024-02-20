package org.timemates.api.rsocket.timers.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.rsocket.timers.rs
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.timers.requests.CreateTimerRequest
import org.timemates.sdk.timers.types.value.TimerId
import org.timemates.api.timers.requests.CreateTimerRequest as RSCreateTimerRequest

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