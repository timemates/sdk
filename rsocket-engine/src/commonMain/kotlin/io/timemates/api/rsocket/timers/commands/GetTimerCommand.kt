package io.timemates.api.rsocket.timers.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.requests.RSocketGetTimerRequest
import io.timemates.api.rsocket.timers.types.sdk
import io.timemates.api.rsocket.timers.types.serializable
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.requests.GetTimerRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.api.rsocket.timers.requests.RSocketCreateTimerRequest as RSocketCreateTimerRequest

internal object GetTimerCommand : RSocketCommand<GetTimerRequest, GetTimerRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: GetTimerRequest): GetTimerRequest.Result {
        return rSocket.requestResponse(
            route = "timers.get",
            data = RSocketGetTimerRequest(
                timerId = input.timerId.long,
            ),
            accessHash = input.accessHash.string,
        ).let { result ->
            GetTimerRequest.Result(result.timer.sdk())
        }
    }
}