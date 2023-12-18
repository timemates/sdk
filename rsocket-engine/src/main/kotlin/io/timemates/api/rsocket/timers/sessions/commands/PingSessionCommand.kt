package io.timemates.api.rsocket.timers.sessions.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.fireAndForget
import io.timemates.api.rsocket.serializable.requests.timers.sessions.LeaveSessionRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.PingSessionRequest

internal object PingSessionCommand : RSocketCommand<PingSessionRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: PingSessionRequest): Empty {
        return rSocket.fireAndForget(
            route = "timers.sessions.ping",
            data = LeaveSessionRequest,
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}