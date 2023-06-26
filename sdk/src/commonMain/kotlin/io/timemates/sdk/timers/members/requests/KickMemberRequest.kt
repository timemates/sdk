package io.timemates.sdk.timers.members.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.users.profile.types.value.UserId

public data class KickMemberRequest(
    val accessHash: AccessHash,
    val timerId: TimerId,
    val userId: UserId,
) : TimeMatesRequest<Empty>()