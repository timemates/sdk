package org.timemates.api.rsocket.authorizations.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import org.timemates.sdk.common.types.Empty
import com.google.protobuf.Empty.Companion as RSEmpty

internal object TerminateCurrentAuthorizationCommand : RSocketCommand<TerminateCurrentAuthorizationSessionRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: TerminateCurrentAuthorizationSessionRequest): Empty {
        return apis.auth.terminateAuthorization(
            message = RSEmpty.Default,
            input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}