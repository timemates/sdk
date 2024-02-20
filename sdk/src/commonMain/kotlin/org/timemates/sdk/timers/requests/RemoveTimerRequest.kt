package org.timemates.sdk.timers.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.value.TimerId

public data class RemoveTimerRequest(
    val accessHash: AccessHash,
    val timerId: TimerId,
) : TimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<RemoveTimerRequest>

    override val requestKey: Key get() = Key
}