package org.timemates.sdk.timers.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.TimerSettings
import org.timemates.sdk.timers.types.value.TimerDescription
import org.timemates.sdk.timers.types.value.TimerId
import org.timemates.sdk.timers.types.value.TimerName

public data class CreateTimerRequest(
    override val accessHash: AccessHash,
    val name: TimerName,
    val description: TimerDescription,
    val settings: TimerSettings,
) : AuthorizedTimeMatesRequest<CreateTimerRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<CreateTimerRequest>

    override val requestKey: Key get() = Key

    public data class Result(val timerId: TimerId) : TimeMatesEntity()
}
