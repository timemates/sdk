package org.timemates.sdk.timers.sessions.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.Timer
import org.timemates.sdk.timers.types.value.TimerId
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