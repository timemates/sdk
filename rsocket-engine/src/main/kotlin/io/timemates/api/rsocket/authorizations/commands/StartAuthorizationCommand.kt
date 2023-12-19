package io.timemates.api.rsocket.authorizations.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.rs
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.authorization.email.requests.StartAuthorizationRequest
import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.types.value.Count
import kotlinx.datetime.Instant
import io.timemates.api.authorizations.requests.StartAuthorizationRequest as RSStartAuthorizationRequest

internal object StartAuthorizationCommand : RSocketCommand<StartAuthorizationRequest, StartAuthorizationRequest.Result> {
    override suspend fun execute(
        apis: ApiContainer,
        input: StartAuthorizationRequest,
    ): StartAuthorizationRequest.Result {
        return apis.auth.startAuthorization(
            message = RSStartAuthorizationRequest(
                input.emailAddress.string,
                input.metadata.rs(),
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