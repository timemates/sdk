package io.timemates.api.rsocket.authorizations.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.authorizations.types.sdk
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.sdk.authorization.email.requests.ConfigureNewAccountRequest
import io.timemates.api.rsocket.authorizations.requests.ConfigureAccountRequest as RSocketConfigureAccountRequest

internal object ConfigureNewAccountCommand : RSocketCommand<ConfigureNewAccountRequest, ConfigureNewAccountRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: ConfigureNewAccountRequest): ConfigureNewAccountRequest.Result {
        return rSocket.requestResponse<RSocketConfigureAccountRequest, RSocketConfigureAccountRequest.Result>(
            route = "authorizations.account.configure",
            data = RSocketConfigureAccountRequest(
                verificationHash = input.verificationHash.string,
                name = input.name.string,
                description = input.description?.string,
            )
        ).let { result ->
            ConfigureNewAccountRequest.Result(
                authorization = result.authorization.sdk()
            )
        }
    }
}