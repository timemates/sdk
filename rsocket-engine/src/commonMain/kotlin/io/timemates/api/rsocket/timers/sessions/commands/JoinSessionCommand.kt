package io.timemates.api.rsocket.timers.sessions.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.sessions.requests.RSocketJoinSessionRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.JoinTimerSessionRequest

internal object JoinSessionCommand : RSocketCommand<JoinTimerSessionRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: JoinTimerSessionRequest): Empty {
        return rSocket.requestResponse(
            route = "timers.sessions.join",
            data = RSocketJoinSessionRequest(
                timerId = input.timerId.long,
            ),
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}