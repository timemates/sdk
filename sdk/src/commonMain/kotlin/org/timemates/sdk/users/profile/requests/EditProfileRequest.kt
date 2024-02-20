package org.timemates.sdk.users.profile.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.users.profile.types.Avatar
import org.timemates.sdk.users.profile.types.value.UserDescription
import org.timemates.sdk.users.profile.types.value.UserName

public data class EditProfileRequest(
    override val accessHash: AccessHash,
    val name: UserName?,
    val description: UserDescription?,
    val avatar: Avatar?,
) : AuthorizedTimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<EditProfileRequest>

    override val requestKey: Key get() = Key
}