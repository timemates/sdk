package org.timemates.api.rsocket.timers.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.timers.requests.EditTimerRequest
import org.timemates.api.timers.requests.EditTimerRequest as RSEditTimerRequest

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