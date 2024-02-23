package org.timemates.api.rsocket.timers.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.timers.requests.RemoveTimerRequest
import org.timemates.api.timers.requests.RemoveTimerRequest as RSRemoveTimerRequest

internal object RemoveTimerCommand : RSocketCommand<RemoveTimerRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: RemoveTimerRequest): Empty {
        return apis.timers.removeTimer(
            message = RSRemoveTimerRequest {
                timerId = input.timerId.long
            },
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}