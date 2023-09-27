package io.timemates.api.rsocket.timers.sessions.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.sessions.requests.RSocketStartSessionRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.StartTimerRequest

internal object StartSessionCommand : RSocketCommand<StartTimerRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: StartTimerRequest): Empty {
        return rSocket.requestResponse(
            route = "timers.sessions.start",
            data = RSocketStartSessionRequest(
                timerId = input.timerId.long,
            ),
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}