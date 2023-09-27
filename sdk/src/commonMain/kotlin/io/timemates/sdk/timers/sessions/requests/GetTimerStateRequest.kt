package io.timemates.sdk.timers.sessions.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.Timer
import io.timemates.sdk.timers.types.value.TimerId
import kotlinx.coroutines.flow.Flow

/**
 * Gets timer session state.
 */
public data class GetTimerStateRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
) : AuthorizedTimeMatesRequest<GetTimerStateRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<GetTimerStateRequest>

    override val requestKey: Key get() = Key

    public data class Result(val flow: Flow<Timer.State>) : TimeMatesEntity()
}