package org.timemates.sdk.users.profile.types

import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.users.profile.types.value.EmailAddress
import org.timemates.sdk.users.profile.types.value.UserDescription
import org.timemates.sdk.users.profile.types.value.UserId
import org.timemates.sdk.users.profile.types.value.UserName

/**
 * Represents a user entity in the system.
 *
 * @property id The unique identifier of the user.
 * @property name The name of the user.
 * @property description The description of the user.
 * @property avatar The user's avatar file.
 * @property emailAddress The email address associated with the user (null if not available).
 */
public data class User(
    val id: UserId,
    val name: UserName,
    val description: UserDescription?,
    val emailAddress: EmailAddress?,
    val avatar: Avatar?,
) : TimeMatesEntity()