package io.timemates.sdk.timers.sessions.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.Timer

/**
 * Gets current timer session.
 */
public data class GetCurrentUserSessionRequest(
    val accessHash: AccessHash,
) : TimeMatesRequest<Timer>()