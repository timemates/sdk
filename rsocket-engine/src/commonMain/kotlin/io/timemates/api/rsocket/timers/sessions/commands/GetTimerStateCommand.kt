package io.timemates.api.rsocket.timers.sessions.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.common.ext.requestStream
import io.timemates.api.rsocket.timers.requests.RSocketGetTimerRequest
import io.timemates.api.rsocket.timers.sessions.requests.RSocketGetCurrentTimerStateRequest
import io.timemates.api.rsocket.timers.sessions.types.sdk
import io.timemates.api.rsocket.timers.types.sdk
import io.timemates.api.rsocket.timers.types.serializable
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.requests.GetTimerRequest
import io.timemates.sdk.timers.sessions.requests.GetTimerStateRequest
import io.timemates.sdk.timers.types.value.TimerId
import kotlinx.coroutines.flow.map
import io.timemates.api.rsocket.timers.requests.RSocketCreateTimerRequest as RSocketCreateTimerRequest

internal object GetTimerStateCommand : RSocketCommand<GetTimerStateRequest, GetTimerStateRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: GetTimerStateRequest): GetTimerStateRequest.Result {
        return rSocket.requestStream(
            route = "timers.get",
            data = RSocketGetCurrentTimerStateRequest(
                timerId = input.timerId.long,
            ),
            accessHash = input.accessHash.string,
        ).let { result ->
            GetTimerStateRequest.Result(result.map { it.sdk() })
        }
    }
}