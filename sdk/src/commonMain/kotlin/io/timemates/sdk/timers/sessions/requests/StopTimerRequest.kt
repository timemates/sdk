package io.timemates.sdk.timers.sessions.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.value.TimerId

/**
 * Stops timer session.
 */
public data class StopTimerRequest(
    val accessHash: AccessHash,
    val timerId: TimerId,
) : TimeMatesRequest<Empty>()