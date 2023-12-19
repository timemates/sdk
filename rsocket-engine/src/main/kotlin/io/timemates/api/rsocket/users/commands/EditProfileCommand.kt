package io.timemates.api.rsocket.users.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.users.profile.requests.EditProfileRequest
import io.timemates.sdk.users.profile.types.Avatar
import io.timemates.api.users.requests.EditUserRequest as RSEditUserRequest

internal object EditProfileCommand : RSocketCommand<EditProfileRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: EditProfileRequest): Empty {
        return apis.users.setUser(
            message = RSEditUserRequest(
                gravatarId = (input.avatar as? Avatar.GravatarId)?.string.orEmpty(),
                name = input.name?.string.orEmpty(),
                description = input.description?.string.orEmpty(),
            ),
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}