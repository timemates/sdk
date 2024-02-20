package org.timemates.api.rsocket.timers.members.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.timers.members.requests.KickMemberRequest
import org.timemates.api.timers.members.requests.KickMemberRequest as RSKickMemberRequest

internal object KickTimerMemberCommand : RSocketCommand<KickMemberRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: KickMemberRequest): Empty {
        return apis.timers.kickMember(
            message = RSKickMemberRequest(timerId = input.timerId.long),
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}