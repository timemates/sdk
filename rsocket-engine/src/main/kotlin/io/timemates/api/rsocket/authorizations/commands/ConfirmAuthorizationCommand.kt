package io.timemates.api.rsocket.authorizations.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.sdk
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.authorization.email.requests.ConfirmAuthorizationRequest
import io.timemates.api.authorizations.requests.ConfirmAuthorizationRequest as RSConfirmAuthorizationRequest

internal object ConfirmAuthorizationCommand : RSocketCommand<ConfirmAuthorizationRequest, ConfirmAuthorizationRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: ConfirmAuthorizationRequest): ConfirmAuthorizationRequest.Result {
        return apis.auth.confirmAuthorization(
            message = RSConfirmAuthorizationRequest(
                input.verificationHash.string, input.confirmationCode.string
            )
        ).let { result ->
            ConfirmAuthorizationRequest.Result(
                isNewAccount = result.isNewAccount,
                authorization = result.authorization?.sdk(),
            )
        }
    }
}