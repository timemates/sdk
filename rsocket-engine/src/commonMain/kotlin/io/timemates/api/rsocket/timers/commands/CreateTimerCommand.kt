package io.timemates.api.rsocket.timers.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.types.serializable
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.api.rsocket.timers.requests.RSocketCreateTimerRequest as RSocketCreateTimerRequest

internal object CreateTimerCommand : RSocketCommand<CreateTimerRequest, CreateTimerRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: CreateTimerRequest): CreateTimerRequest.Result {
        return rSocket.requestResponse(
            route = "timers.create",
            data = RSocketCreateTimerRequest(
                name = input.name.string,
                description = input.description.string,
                settings = input.settings.serializable()
            ),
            accessHash = input.accessHash.string,
        ).let { result ->
            CreateTimerRequest.Result(TimerId.createOrThrow(result.timerId))
        }
    }
}