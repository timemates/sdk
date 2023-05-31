package io.timemates.sdk.timers.members.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.common.types.value.PageToken
import io.timemates.sdk.timers.types.value.TimerId

public data class KickMemberRequest(
    val accessHash: AccessHash,
    val timerId: TimerId,
    val pageToken: PageToken,
) : TimeMatesRequest<Empty>()