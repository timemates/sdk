package io.timemates.api.rsocket.timers.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.requests.EditTimerRequest
import io.timemates.api.rsocket.timers.requests.RSocketEditTimerRequest as RSocketEditTimerRequest

internal object EditTimerCommand : RSocketCommand<EditTimerRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: EditTimerRequest): Empty {
        return rSocket.requestResponse(
            route = "timers.edit",
            data = RSocketEditTimerRequest(
                timerId = input.timerId.long,
                name = input.name?.string,
            ),
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}