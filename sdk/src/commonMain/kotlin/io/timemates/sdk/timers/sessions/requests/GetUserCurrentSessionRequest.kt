package io.timemates.sdk.timers.sessions.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.Timer

/**
 * Gets current timer session.
 */
public data class GetUserCurrentSessionRequest(
    override val accessHash: AccessHash,
) : AuthorizedTimeMatesRequest<Timer>() {

    public companion object Key : TimeMatesRequest.Key<GetUserCurrentSessionRequest>

    override val requestKey: Key get() = Key
}