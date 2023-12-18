package io.timemates.api.rsocket.timers.members.invites.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.members.invites.requests.RemoveInviteRequest
import io.timemates.api.timers.members.invites.requests.RemoveInviteRequest as RSRemoveInviteRequest

internal object RemoveInviteCommand : RSocketCommand<RemoveInviteRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: RemoveInviteRequest): Empty {
        return apis.timers.removeInvite(
            message = RSRemoveInviteRequest(
                timerId = input.timerId.long,
                inviteCode = input.inviteCode.string,
            ),
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}