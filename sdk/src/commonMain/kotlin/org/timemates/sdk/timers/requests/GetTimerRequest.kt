package org.timemates.sdk.timers.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.Timer
import org.timemates.sdk.timers.types.value.TimerId

public data class GetTimerRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
) : AuthorizedTimeMatesRequest<GetTimerRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<GetTimerRequest>

    override val requestKey: Key get() = Key

    public data class Result(
        val timer: Timer,
    ) : TimeMatesEntity()
}