package io.timemates.api.rsocket.authorizations.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.authorizations.types.sdk
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.sdk.authorization.email.requests.ConfirmAuthorizationRequest
import io.timemates.api.rsocket.authorizations.requests.ConfirmAuthorizationRequest as RSocketConfirmAuthorizationRequest

internal object ConfirmAuthorizationCommand : RSocketCommand<ConfirmAuthorizationRequest, ConfirmAuthorizationRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: ConfirmAuthorizationRequest): ConfirmAuthorizationRequest.Result {
        return rSocket.requestResponse(
            route = "authorizations.email.confirm",
            data = RSocketConfirmAuthorizationRequest(
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