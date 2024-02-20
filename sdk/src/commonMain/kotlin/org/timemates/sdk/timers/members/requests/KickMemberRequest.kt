package org.timemates.sdk.timers.members.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.value.TimerId
import org.timemates.sdk.users.profile.types.value.UserId

public data class KickMemberRequest(
    val accessHash: AccessHash,
    val timerId: TimerId,
    val userId: UserId,
) : TimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<KickMemberRequest>

    override val requestKey: Key get() = Key
}