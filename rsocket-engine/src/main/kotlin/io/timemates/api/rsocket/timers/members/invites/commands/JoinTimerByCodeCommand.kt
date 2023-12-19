package io.timemates.api.rsocket.timers.members.invites.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.timers.sdk
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.timers.members.invites.requests.JoinTimerByCodeRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.api.timers.requests.JoinTimerByInviteCodeRequest as RSJoinTimerByInviteCodeRequest

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