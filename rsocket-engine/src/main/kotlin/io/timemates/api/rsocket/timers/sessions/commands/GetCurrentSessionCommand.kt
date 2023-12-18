package io.timemates.api.rsocket.timers.sessions.commands

import com.google.protobuf.Empty
import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.timers.sdk
import io.timemates.sdk.timers.sessions.requests.GetUserCurrentSessionRequest
import io.timemates.sdk.timers.types.Timer

internal object GetCurrentSessionCommand : RSocketCommand<GetUserCurrentSessionRequest, Timer> {
    override suspend fun execute(apis: ApiContainer, input: GetUserCurrentSessionRequest): Timer {
        return apis.timerSessions.getCurrentTimerSession(
            message = Empty.Default,
            extra = input.accessHash.toExtra(),
        ).sdk()
    }
}