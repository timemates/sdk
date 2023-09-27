package io.timemates.api.rsocket.timers.members.invites.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.members.invites.requests.RSocketCreateInviteRequest
import io.timemates.api.rsocket.timers.types.serializable
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.timers.members.invites.requests.CreateInviteRequest
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.api.rsocket.timers.requests.RSocketCreateTimerRequest as RSocketCreateTimerRequest

internal object CreateInviteCommand : RSocketCommand<CreateInviteRequest, CreateInviteRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: CreateInviteRequest): CreateInviteRequest.Result {
        return rSocket.requestResponse(
            route = "timers.members.invites.create",
            data = RSocketCreateInviteRequest(
                timerId = input.timerId.long,
                maxJoiners = input.maxJoinersCount.int,
            ),
            accessHash = input.accessHash.string,
        ).let { result ->
            CreateInviteRequest.Result(InviteCode.createOrThrow(result.inviteCode))
        }
    }
}