package org.timemates.api.rsocket.users

import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.users.profile.types.Avatar
import org.timemates.sdk.users.profile.types.value.EmailAddress
import org.timemates.sdk.users.profile.types.value.UserDescription
import org.timemates.sdk.users.profile.types.value.UserId
import org.timemates.sdk.users.profile.types.value.UserName
import org.timemates.api.users.types.User as RSUser
import org.timemates.sdk.users.profile.types.User as SdkUser

internal fun RSUser.sdk(): SdkUser {
    return SdkUser(
        id = UserId.factory.createOrThrow(id),
        name = UserName.factory.createOrThrow(name),
        description = description.takeIf { it.isNotEmpty() }?.let { UserDescription.factory.createOrThrow(it) },
        emailAddress = email.takeIf { it.isNotEmpty() }?.let { EmailAddress.factory.createOrThrow(it) },
        avatar = gravatarId.takeIf { it.isNotBlank() }?.let { Avatar.GravatarId.factory.createOrThrow(it) },
    )
}