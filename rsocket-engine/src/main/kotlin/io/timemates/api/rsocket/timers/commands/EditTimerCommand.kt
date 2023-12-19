package io.timemates.api.rsocket.timers.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.requests.EditTimerRequest
import io.timemates.api.timers.requests.EditTimerRequest as RSEditTimerRequest

internal object EditTimerCommand : RSocketCommand<EditTimerRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: EditTimerRequest): Empty {
        return apis.timers.editTimer(
            message = RSEditTimerRequest(
                timerId = input.timerId.long,
                name = input.name?.string.orEmpty(),
            ),
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}