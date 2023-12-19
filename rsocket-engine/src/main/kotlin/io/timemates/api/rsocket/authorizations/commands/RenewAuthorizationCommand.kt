package io.timemates.api.rsocket.authorizations.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.sdk
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.authorization.sessions.requests.RenewAuthorizationRequest
import io.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import io.timemates.api.authorizations.requests.RenewAuthorizationRequest as RSRenewAuthorizationRequest

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