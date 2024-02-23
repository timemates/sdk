package org.timemates.api.rsocket.users.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.users.profile.requests.EditProfileRequest
import org.timemates.sdk.users.profile.types.Avatar
import org.timemates.api.users.requests.EditUserRequest as RSEditUserRequest

internal object EditProfileCommand : RSocketCommand<EditProfileRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: EditProfileRequest): Empty {
        return apis.users.setUser(
            message = RSEditUserRequest {
                gravatarId = (input.avatar as? Avatar.GravatarId)?.string.orEmpty()
                name = input.name?.string.orEmpty()
                description = input.description?.string.orEmpty()
            },
            extra = input.accessHash.toExtra(),
        ).let { _ -> Empty }
    }
}