package io.timemates.api.grpc.mappers

import io.timemates.api.users.types.UserOuterClass.User
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.files.types.value.FileId
import io.timemates.sdk.users.profile.types.value.EmailAddress
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserId
import io.timemates.sdk.users.profile.types.value.UserName
import io.timemates.sdk.users.profile.types.User as SdkUser

internal class UsersMapper {
    fun grpcUserToSdkUser(user: User): SdkUser = with(user) {
        return SdkUser(
            id = UserId.createOrThrow(id),
            name = UserName.createOrThrow(name),
            description = UserDescription.createOrThrow(description),
            avatarFileId = FileId.createOrThrow(avatarId),
            emailAddress = email?.takeIf { it.isNotEmpty() }?.let { EmailAddress.createOrThrow(it) }
        )
    }
}