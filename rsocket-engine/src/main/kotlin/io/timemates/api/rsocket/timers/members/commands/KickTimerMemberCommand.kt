package io.timemates.api.rsocket.timers.members.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.members.requests.KickMemberRequest
import io.timemates.api.timers.members.requests.KickMemberRequest as RSKickMemberRequest

internal object KickTimerMemberCommand : RSocketCommand<KickMemberRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: KickMemberRequest): Empty {
        return apis.timers.kickMember(
            message = RSKickMemberRequest(timerId = input.timerId.long),
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}