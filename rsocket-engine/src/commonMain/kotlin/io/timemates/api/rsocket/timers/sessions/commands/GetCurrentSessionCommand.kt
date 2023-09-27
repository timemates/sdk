package io.timemates.api.rsocket.timers.sessions.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.sessions.requests.RSocketGetCurrentSessionRequest
import io.timemates.api.rsocket.timers.types.sdk
import io.timemates.sdk.timers.sessions.requests.GetUserCurrentSessionRequest
import io.timemates.sdk.timers.types.Timer

internal object GetCurrentSessionCommand : RSocketCommand<GetUserCurrentSessionRequest, Timer> {
    override suspend fun execute(rSocket: RSocket, input: GetUserCurrentSessionRequest): Timer {
        return rSocket.requestResponse(
            route = "timers.sessions.ping",
            data = RSocketGetCurrentSessionRequest,
            accessHash = input.accessHash.string,
        ).timer.sdk()
    }
}