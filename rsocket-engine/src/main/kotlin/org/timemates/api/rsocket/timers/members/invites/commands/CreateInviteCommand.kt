package org.timemates.api.rsocket.timers.members.invites.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.timers.members.invites.requests.InviteMemberRequest
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.timers.members.invites.requests.CreateInviteRequest
import org.timemates.sdk.timers.members.invites.types.value.InviteCode

internal object CreateInviteCommand : RSocketCommand<CreateInviteRequest, CreateInviteRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: CreateInviteRequest): CreateInviteRequest.Result {
        return apis.timers.createInvite(
            message = InviteMemberRequest {
                timerId = input.timerId.long
                maxJoiners = input.maxJoinersCount.int
            },
            extra = input.accessHash.toExtra(),
        ).let { result ->
            CreateInviteRequest.Result(InviteCode.factory.createOrThrow(result.inviteCode))
        }
    }
}