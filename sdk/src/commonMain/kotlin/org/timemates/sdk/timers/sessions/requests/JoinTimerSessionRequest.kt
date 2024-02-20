package org.timemates.sdk.timers.sessions.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.value.TimerId

public data class JoinTimerSessionRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
) : AuthorizedTimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<JoinTimerSessionRequest>

    override val requestKey: Key get() = Key
}