package org.timemates.sdk.timers.sessions.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.Timer

/**
 * Gets current timer session.
 */
public data class GetUserCurrentSessionRequest(
    override val accessHash: AccessHash,
) : AuthorizedTimeMatesRequest<Timer>() {

    public companion object Key : TimeMatesRequest.Key<GetUserCurrentSessionRequest>

    override val requestKey: Key get() = Key
}