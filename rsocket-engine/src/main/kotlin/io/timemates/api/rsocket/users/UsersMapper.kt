package io.timemates.api.rsocket.users

import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.users.profile.types.Avatar
import io.timemates.sdk.users.profile.types.value.EmailAddress
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserId
import io.timemates.sdk.users.profile.types.value.UserName
import io.timemates.api.users.types.User as RSUser
import io.timemates.sdk.users.profile.types.User as SdkUser

internal fun RSUser.sdk(): SdkUser {
    return SdkUser(
        id = UserId.createOrThrow(id),
        name = UserName.createOrThrow(name),
        description = description.takeIf { it.isNotEmpty() }?.let { UserDescription.createOrThrow(it) },
        emailAddress = email.takeIf { it.isNotEmpty() }?.let { EmailAddress.createOrThrow(it) },
        avatar = gravatarId.takeIf { it.isNotBlank() }?.let { Avatar.GravatarId.createOrThrow(it) },
    )
}