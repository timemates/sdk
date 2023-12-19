package io.timemates.api.rsocket.timers.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.requests.RemoveTimerRequest
import io.timemates.api.timers.requests.RemoveTimerRequest as RSRemoveTimerRequest

internal object RemoveTimerCommand : RSocketCommand<RemoveTimerRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: RemoveTimerRequest): Empty {
        return apis.timers.removeTimer(
            message = RSRemoveTimerRequest(input.timerId.long),
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}