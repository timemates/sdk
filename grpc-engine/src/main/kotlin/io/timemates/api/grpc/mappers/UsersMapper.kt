package io.timemates.api.grpc.mappers

import io.timemates.api.users.types.UserOuterClass
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.users.profile.types.Avatar
import io.timemates.sdk.users.profile.types.value.EmailAddress
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserId
import io.timemates.sdk.users.profile.types.value.UserName
import io.timemates.sdk.users.profile.types.User as SdkUser

internal class UsersMapper {
    fun grpcUserToSdkUser(user: UserOuterClass.User): SdkUser = with(user) {
        return SdkUser(
            id = UserId.createOrThrow(id),
            name = UserName.createOrThrow(name),
            description = UserDescription.createOrThrow(description),
            emailAddress = email?.takeIf { it.isNotEmpty() }?.let { EmailAddress.createOrThrow(it) },
            avatar = gravatarId?.let { Avatar.GravatarId.createOrThrow(it) } ?:
                avatarId?.let { Avatar.FileId.createOrThrow(it) }
        )
    }
}