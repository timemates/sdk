package org.timemates.sdk.timers.members.invites.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.members.invites.types.value.InviteCode
import org.timemates.sdk.timers.types.Timer
import org.timemates.sdk.timers.types.value.TimerId

public data class JoinTimerByCodeRequest(
    public val code: InviteCode,
    public val accessHash: AccessHash,
) : TimeMatesRequest<JoinTimerByCodeRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<JoinTimerByCodeRequest>

    override val requestKey: Key get() = Key

    public data class Result(val timer: Timer) : TimeMatesEntity()
}