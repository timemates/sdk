package io.timemates.api.rsocket.authorizations.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import io.timemates.sdk.common.types.Empty
import com.google.protobuf.Empty.Companion as RSEmpty

internal object TerminateCurrentAuthorizationCommand : RSocketCommand<TerminateCurrentAuthorizationSessionRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: TerminateCurrentAuthorizationSessionRequest): Empty {
        return apis.auth.terminateAuthorization(
            message = RSEmpty.Default,
            input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}