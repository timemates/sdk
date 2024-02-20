package org.timemates.api.rsocket.timers.sessions.commands

import com.google.protobuf.Empty
import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.rsocket.timers.sdk
import org.timemates.sdk.timers.sessions.requests.GetUserCurrentSessionRequest
import org.timemates.sdk.timers.types.Timer

internal object GetCurrentSessionCommand : RSocketCommand<GetUserCurrentSessionRequest, Timer> {
    override suspend fun execute(apis: ApiContainer, input: GetUserCurrentSessionRequest): Timer {
        return apis.timerSessions.getCurrentTimerSession(
            message = Empty.Default,
            extra = input.accessHash.toExtra(),
        ).sdk()
    }
}