package org.timemates.api.rsocket.timers.members.invites.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.rsocket.timers.sdk
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.timers.members.invites.requests.JoinTimerByCodeRequest
import org.timemates.sdk.timers.types.value.TimerId
import org.timemates.api.timers.requests.JoinTimerByInviteCodeRequest as RSJoinTimerByInviteCodeRequest

internal object JoinTimerByCodeCommand : RSocketCommand<JoinTimerByCodeRequest, JoinTimerByCodeRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: JoinTimerByCodeRequest): JoinTimerByCodeRequest.Result {
        return apis.timers.joinByInvite(
            message = RSJoinTimerByInviteCodeRequest(
                inviteCode = input.code.string,
            ),
            extra = input.accessHash.toExtra(),
        ).let { result ->
            JoinTimerByCodeRequest.Result(result.timer!!.sdk())
        }
    }
}