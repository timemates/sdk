package io.timemates.api.rsocket.timers.sessions.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.PingSessionRequest
import com.google.protobuf.Empty.Companion as RSEmpty

internal object PingSessionCommand : RSocketCommand<PingSessionRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: PingSessionRequest): Empty {
        return apis.timerSessions.pingSession(
            message = RSEmpty.Default,
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}