package io.timemates.api.rsocket.users.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.users.requests.RSocketEditUserProfileRequest
import io.timemates.api.rsocket.users.types.serializable
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.users.profile.requests.EditProfileRequest

internal object EditProfileCommand : RSocketCommand<EditProfileRequest, Empty> {
    override suspend fun execute(rSocket: RSocket, input: EditProfileRequest): Empty {
        return rSocket.requestResponse(
            route = "users.profile.edit",
            data = RSocketEditUserProfileRequest(
                name = input.name?.string,
                description = input.description?.string,
                avatar = input.avatar?.serializable(),
            ),
            accessHash = input.accessHash.string,
        ).let { _ -> Empty }
    }
}