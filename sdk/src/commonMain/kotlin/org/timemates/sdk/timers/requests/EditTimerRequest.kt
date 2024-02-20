package org.timemates.sdk.timers.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.TimerSettings
import org.timemates.sdk.timers.types.value.TimerDescription
import org.timemates.sdk.timers.types.value.TimerId
import org.timemates.sdk.timers.types.value.TimerName

public data class EditTimerRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
    val name: TimerName? = null,
    val description: TimerDescription? = null,
    val settings: TimerSettings.Patch? = null,
) : AuthorizedTimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<EditTimerRequest>

    override val requestKey: Key get() = Key
}