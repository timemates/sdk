package io.timemates.sdk.users.profile.types

import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.users.profile.types.value.EmailAddress
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserId
import io.timemates.sdk.users.profile.types.value.UserName

/**
 * Represents a user entity in the system.
 *
 * @property id The unique identifier of the user.
 * @property name The name of the user.
 * @property description The description of the user.
 * @property avatarFileId The unique identifier of the user's avatar file.
 * @property emailAddress The email address associated with the user (null if not available).
 */
public data class User(
    val id: UserId,
    val name: UserName,
    val description: UserDescription,
    val emailAddress: EmailAddress?,
    val avatar: Avatar?,
) : TimeMatesEntity()