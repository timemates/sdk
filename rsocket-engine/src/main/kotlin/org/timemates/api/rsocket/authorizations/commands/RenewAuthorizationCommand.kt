package org.timemates.api.rsocket.authorizations.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.sdk
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.authorization.sessions.requests.RenewAuthorizationRequest
import org.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import org.timemates.api.authorizations.requests.RenewAuthorizationRequest as RSRenewAuthorizationRequest

@OptIn(ExperimentalTimeMatesApi::class)
internal object RenewAuthorizationCommand : RSocketCommand<RenewAuthorizationRequest, RenewAuthorizationRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: RenewAuthorizationRequest): RenewAuthorizationRequest.Result {
        return apis.auth.renewAuthorization(
            message = RSRenewAuthorizationRequest(input.refreshHash.string),
        ).let { result ->
            RenewAuthorizationRequest.Result(
                authorization = result.authorization!!.sdk()
            )
        }
    }
}