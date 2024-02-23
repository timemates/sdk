package org.timemates.api.rsocket.authorizations.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.sdk
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.authorization.email.requests.ConfirmAuthorizationRequest
import org.timemates.api.authorizations.requests.ConfirmAuthorizationRequest as RSConfirmAuthorizationRequest

internal object ConfirmAuthorizationCommand : RSocketCommand<ConfirmAuthorizationRequest, ConfirmAuthorizationRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: ConfirmAuthorizationRequest): ConfirmAuthorizationRequest.Result {
        return apis.auth.confirmAuthorization(
            message = RSConfirmAuthorizationRequest {
                verificationHash = input.verificationHash.string
                confirmationCode = input.confirmationCode.string
            }
        ).let { result ->
            ConfirmAuthorizationRequest.Result(
                isNewAccount = result.isNewAccount,
                authorization = result.authorization?.sdk(),
            )
        }
    }
}