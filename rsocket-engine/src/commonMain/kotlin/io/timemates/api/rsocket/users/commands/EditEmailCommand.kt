package io.timemates.api.rsocket.users.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.users.requests.EditUserProfileRequest
import io.timemates.api.rsocket.users.types.serializable
import io.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.users.profile.requests.EditProfileRequest
import io.timemates.sdk.users.settings.requests.EditEmailRequest

@OptIn(ExperimentalTimeMatesApi::class)
internal object EditEmailCommand : RSocketCommand<EditEmailRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: EditEmailRequest): Empty {
        return rSocket.requestResponse(
            route = "users.email.edit",
            data = io.timemates.api.rsocket.users.requests.EditEmailRequest(
                email = input.newEmail.string,
            ),
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}