package io.timemates.sdk.timers.sessions.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.value.TimerId

public data class ConfirmTimerRoundRequest(
    val accessHash: AccessHash,
) : TimeMatesRequest<Empty>()