package io.timemates.api.rsocket.timers.sessions.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.sessions.requests.RSocketConfirmSessionRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.ConfirmTimerRoundRequest

internal object ConfirmSessionCommand : RSocketCommand<ConfirmTimerRoundRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: ConfirmTimerRoundRequest): Empty {
        return rSocket.requestResponse(
            route = "timers.sessions.attendance.confirm",
            data = RSocketConfirmSessionRequest,
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}