package io.timemates.sdk.timers.members.invites.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import io.timemates.sdk.timers.types.Timer
import io.timemates.sdk.timers.types.value.TimerId

public data class JoinTimerByCodeRequest(
    public val code: InviteCode,
    public val accessHash: AccessHash,
) : TimeMatesRequest<JoinTimerByCodeRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<JoinTimerByCodeRequest>

    override val requestKey: Key get() = Key

    public data class Result(val timer: Timer) : TimeMatesEntity()
}