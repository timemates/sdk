package io.timemates.api.rsocket.authorizations.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.sdk
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.authorization.email.requests.ConfigureNewAccountRequest
import io.timemates.api.users.requests.CreateProfileRequest as RSCreateProfileRequest

internal object ConfigureNewAccountCommand : RSocketCommand<ConfigureNewAccountRequest, ConfigureNewAccountRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: ConfigureNewAccountRequest): ConfigureNewAccountRequest.Result {
        return apis.auth.createProfile(
            message = RSCreateProfileRequest(
                verificationHash = input.verificationHash.string,
                name = input.name.string,
                description = input.description?.string.orEmpty(),
            )
        ).let { result ->
            ConfigureNewAccountRequest.Result(
                authorization = result.authorization!!.sdk()
            )
        }
    }
}