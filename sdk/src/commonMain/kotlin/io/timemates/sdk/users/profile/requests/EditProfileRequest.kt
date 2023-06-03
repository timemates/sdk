package io.timemates.sdk.users.profile.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.files.types.value.FileId
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserName

public data class EditProfileRequest(
    val accessToken: AccessHash,
    val avatarId: FileId?,
    val name: UserName?,
    val description: UserDescription?,
) : TimeMatesRequest<Empty>()