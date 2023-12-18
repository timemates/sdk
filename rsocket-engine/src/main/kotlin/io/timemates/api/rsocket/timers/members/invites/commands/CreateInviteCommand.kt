package io.timemates.api.rsocket.timers.members.invites.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.timers.members.invites.requests.InviteMemberRequest
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.timers.members.invites.requests.CreateInviteRequest
import io.timemates.sdk.timers.members.invites.types.value.InviteCode

internal object CreateInviteCommand : RSocketCommand<CreateInviteRequest, CreateInviteRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: CreateInviteRequest): CreateInviteRequest.Result {
        return apis.timers.createInvite(
            message = InviteMemberRequest(
                timerId = input.timerId.long,
                maxJoiners = input.maxJoinersCount.int,
            ),
            extra = input.accessHash.toExtra(),
        ).let { result ->
            CreateInviteRequest.Result(InviteCode.createOrThrow(result.inviteCode))
        }
    }
}