package io.timemates.api.rsocket.authorizations.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.authorizations.requests.TerminateAuthorizationRequest
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import io.timemates.sdk.common.types.Empty

internal object TerminateCurrentAuthorizationCommand : RSocketCommand<TerminateCurrentAuthorizationSessionRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: TerminateCurrentAuthorizationSessionRequest): Empty {
        return rSocket.requestResponse(
            route = "authorizations.terminate",
            data = TerminateAuthorizationRequest.Current,
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}