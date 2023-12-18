package io.timemates.api.rsocket.authorizations.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.authorization.sessions.requests.RenewAuthorizationRequest
import io.timemates.sdk.common.annotations.ExperimentalTimeMatesApi

@OptIn(ExperimentalTimeMatesApi::class)
internal object RenewAuthorizationCommand : RSocketCommand<RenewAuthorizationRequest, RenewAuthorizationRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: RenewAuthorizationRequest): RenewAuthorizationRequest.Result {
        TODO()
//        return apis.auth.requestResponse(
//            route = "authorizations.renew",
//            data = RSocketRenewAuthorizationRequest(input.refreshHash.string),
//        ).let { result ->
//            RenewAuthorizationRequest.Result(
//                AccessHash.createOrThrow(result.accessHash)
//            )
//        }
    }
}