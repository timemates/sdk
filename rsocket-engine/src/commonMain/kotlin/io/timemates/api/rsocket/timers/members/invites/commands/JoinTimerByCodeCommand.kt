package io.timemates.api.rsocket.timers.members.invites.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.members.invites.requests.RSocketCreateInviteRequest
import io.timemates.api.rsocket.timers.members.invites.requests.RSocketJoinTimerByCodeRequest
import io.timemates.api.rsocket.timers.types.serializable
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.timers.members.invites.requests.JoinTimerByCodeRequest
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.api.rsocket.timers.requests.RSocketCreateTimerRequest as RSocketCreateTimerRequest

internal object JoinTimerByCodeCommand : RSocketCommand<JoinTimerByCodeRequest, JoinTimerByCodeRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: JoinTimerByCodeRequest): JoinTimerByCodeRequest.Result {
        return rSocket.requestResponse(
            route = "timers.create",
            data = RSocketJoinTimerByCodeRequest(
                code = input.code.string,
            ),
            accessHash = input.accessHash.string,
        ).let { result ->
            JoinTimerByCodeRequest.Result(TimerId.createOrThrow(result.timerId))
        }
    }
}