package io.timemates.sdk.users.profile.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.users.profile.types.Avatar
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserName

public data class EditProfileRequest(
    override val accessHash: AccessHash,
    val name: UserName?,
    val description: UserDescription?,
    val avatar: Avatar?,
) : AuthorizedTimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<EditProfileRequest>

    override val requestKey: Key get() = Key
}