package io.timemates.api.rsocket.authorizations.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.authorizations.types.serializable
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.sdk.authorization.email.requests.StartAuthorizationRequest
import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.types.value.Count
import kotlinx.datetime.Instant
import io.timemates.api.rsocket.authorizations.requests.RSocketStartAuthorizationRequest as RSocketStartAuthorizationRequest

internal object StartAuthorizationCommand : RSocketCommand<StartAuthorizationRequest, StartAuthorizationRequest.Result> {
    override suspend fun execute(
        rSocket: RSocket,
        input: StartAuthorizationRequest,
    ): StartAuthorizationRequest.Result {
        return rSocket.requestResponse(
            route = "authorizations.email.start",
            data = RSocketStartAuthorizationRequest(
                input.emailAddress.string,
                input.metadata.serializable(),
            )
        ).let { result ->
            StartAuthorizationRequest.Result(
                verificationHash = VerificationHash.createOrThrow(result.verificationHash),
                attempts = Count.createOrThrow(result.attempts),
                expiresAt = Instant.fromEpochMilliseconds(result.expiresAt),
            )
        }
    }
}