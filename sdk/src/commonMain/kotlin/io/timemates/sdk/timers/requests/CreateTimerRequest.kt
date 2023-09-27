package io.timemates.sdk.timers.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.TimerSettings
import io.timemates.sdk.timers.types.value.TimerDescription
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.timers.types.value.TimerName

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
