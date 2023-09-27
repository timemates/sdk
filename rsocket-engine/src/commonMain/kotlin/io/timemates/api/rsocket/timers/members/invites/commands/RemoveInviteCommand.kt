package io.timemates.api.rsocket.timers.members.invites.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.members.invites.requests.RSocketRemoveInviteRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.members.invites.requests.RemoveInviteRequest
import io.timemates.sdk.timers.members.requests.KickMemberRequest
import io.timemates.sdk.timers.requests.RemoveTimerRequest
import io.timemates.api.rsocket.timers.requests.RSocketDeleteTimerRequest as RSocketDeleteTimerRequest

internal object RemoveInviteCommand : RSocketCommand<RemoveInviteRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: RemoveInviteRequest): Empty {
        return rSocket.requestResponse(
            route = "timers.members.kick",
            data = RSocketRemoveInviteRequest(
                timerId = input.timerId.long,
                code = input.inviteCode.string,
            ),
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}