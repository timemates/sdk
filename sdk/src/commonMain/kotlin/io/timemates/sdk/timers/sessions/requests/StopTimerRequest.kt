package io.timemates.sdk.timers.sessions.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.value.TimerId

/**
 * Stops timer session.
 */
public data class StopTimerRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
) : AuthorizedTimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<StopTimerRequest>

    override val requestKey: Key get() = Key
}