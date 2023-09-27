package io.timemates.api.rsocket.users.types

import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.users.profile.types.User
import io.timemates.sdk.users.profile.types.value.EmailAddress
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserId
import io.timemates.sdk.users.profile.types.value.UserName
import kotlinx.serialization.Serializable

@Serializable
internal data class SerializableUser(
    val id: Long,
    val name: String,
    val emailAddress: String?,
    val description: String?,
    val avatar: SerializableAvatar?,
)

internal fun SerializableUser.sdk(): User {
    return User(
        id = UserId.createOrThrow(id),
        name = UserName.createOrThrow(name),
        description = description?.let { UserDescription.createOrThrow(it) },
        emailAddress = emailAddress?.let { EmailAddress.createOrThrow(it) },
        avatar = avatar?.sdk()
    )
}