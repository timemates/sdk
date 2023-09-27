package io.timemates.api.rsocket.timers.sessions.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.sessions.requests.RSocketLeaveSessionRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.LeaveTimerSessionRequest

internal object LeaveSessionCommand : RSocketCommand<LeaveTimerSessionRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: LeaveTimerSessionRequest): Empty {
        return rSocket.requestResponse(
            route = "timers.sessions.leave",
            data = RSocketLeaveSessionRequest,
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}