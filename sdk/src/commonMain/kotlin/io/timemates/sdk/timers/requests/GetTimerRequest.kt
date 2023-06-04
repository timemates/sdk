package io.timemates.sdk.timers.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.Timer
import io.timemates.sdk.timers.types.value.TimerId

public data class GetTimerRequest(
    val accessHash: AccessHash,
    val timerId: TimerId,
) : TimeMatesRequest<GetTimerRequest.Result>() {
    public data class Result(
        val timer: Timer,
    ) : TimeMatesEntity()
}