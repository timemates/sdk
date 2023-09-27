package io.timemates.api.rsocket.authorizations.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.sdk.authorization.sessions.requests.RenewAuthorizationRequest
import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.api.rsocket.authorizations.requests.RSocketRenewAuthorizationRequest as RSocketRenewAuthorizationRequest

@OptIn(ExperimentalTimeMatesApi::class)
internal object RenewAuthorizationCommand : RSocketCommand<RenewAuthorizationRequest, RenewAuthorizationRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: RenewAuthorizationRequest): RenewAuthorizationRequest.Result {
        return rSocket.requestResponse(
            route = "authorizations.renew",
            data = RSocketRenewAuthorizationRequest(input.refreshHash.string),
        ).let { result ->
            RenewAuthorizationRequest.Result(
                AccessHash.createOrThrow(result.accessHash)
            )
        }
    }
}