package org.timemates.api.rsocket.authorizations.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.rs
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.authorization.email.requests.StartAuthorizationRequest
import org.timemates.sdk.authorization.email.types.value.VerificationHash
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.common.types.value.Count
import kotlinx.datetime.Instant
import org.timemates.api.authorizations.requests.StartAuthorizationRequest as RSStartAuthorizationRequest

internal object StartAuthorizationCommand : RSocketCommand<StartAuthorizationRequest, StartAuthorizationRequest.Result> {
    override suspend fun execute(
        apis: ApiContainer,
        input: StartAuthorizationRequest,
    ): StartAuthorizationRequest.Result {
        return apis.auth.startAuthorization(
            message = RSStartAuthorizationRequest {
                emailAddress = input.emailAddress.string
                metadata = input.metadata.rs()
            }
        ).let { result ->
            StartAuthorizationRequest.Result(
                verificationHash = VerificationHash.createOrThrow(result.verificationHash),
                attempts = Count.factory.createOrThrow(result.attempts),
                expiresAt = Instant.fromEpochMilliseconds(result.expiresAt),
            )
        }
    }
}