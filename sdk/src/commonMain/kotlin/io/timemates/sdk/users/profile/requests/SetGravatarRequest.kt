package io.timemates.sdk.users.profile.requests

import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.users.profile.types.value.EmailAddress

public data class SetGravatarRequest(
    val email: EmailAddress,
): TimeMatesRequest<Empty>()