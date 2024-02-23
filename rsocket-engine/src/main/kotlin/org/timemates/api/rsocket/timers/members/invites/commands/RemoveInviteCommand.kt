package org.timemates.api.rsocket.timers.members.invites.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.timers.members.invites.requests.RemoveInviteRequest
import org.timemates.api.timers.members.invites.requests.RemoveInviteRequest as RSRemoveInviteRequest

internal object RemoveInviteCommand : RSocketCommand<RemoveInviteRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: RemoveInviteRequest): Empty {
        return apis.timers.removeInvite(
            message = RSRemoveInviteRequest {
                timerId = input.timerId.long
                inviteCode = input.inviteCode.string
            },
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}