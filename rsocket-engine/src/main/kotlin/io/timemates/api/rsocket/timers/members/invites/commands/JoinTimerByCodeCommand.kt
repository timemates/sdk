package io.timemates.api.rsocket.timers.members.invites.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.timers.members.invites.requests.JoinTimerByCodeRequest

internal object JoinTimerByCodeCommand : RSocketCommand<JoinTimerByCodeRequest, JoinTimerByCodeRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: JoinTimerByCodeRequest): JoinTimerByCodeRequest.Result {
        TODO()
//        return apis.timers(
//            route = "timers.create",
//            data = RSocketJoinTimerByCodeRequest(
//                code = input.code.string,
//            ),
//            accessHash = input.accessHash.string,
//        ).let { result ->
//            JoinTimerByCodeRequest.Result(TimerId.createOrThrow(result.timerId))
//        }
    }
}