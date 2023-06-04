package io.timemates.sdk.timers.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.value.TimerId

public data class RemoveTimerRequest(
    val accessHash: AccessHash,
    val timerId: TimerId,
) : TimeMatesRequest<Empty>()