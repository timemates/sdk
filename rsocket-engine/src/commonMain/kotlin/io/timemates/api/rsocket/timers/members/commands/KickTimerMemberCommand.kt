package io.timemates.api.rsocket.timers.members.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.members.requests.KickMemberRequest
import io.timemates.sdk.timers.requests.RemoveTimerRequest
import io.timemates.api.rsocket.timers.requests.RSocketDeleteTimerRequest as RSocketDeleteTimerRequest

internal object KickTimerMemberCommand : RSocketCommand<KickMemberRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: KickMemberRequest): Empty {
        return rSocket.requestResponse(
            route = "timers.members.kick",
            data = RSocketDeleteTimerRequest(input.timerId.long),
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}