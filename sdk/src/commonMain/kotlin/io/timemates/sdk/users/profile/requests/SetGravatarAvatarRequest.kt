package io.timemates.sdk.users.profile.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.users.profile.types.value.EmailAddress

public data class SetGravatarAvatarRequest(
    val accessToken: AccessHash,
    val email: EmailAddress,
): TimeMatesRequest<Empty>()