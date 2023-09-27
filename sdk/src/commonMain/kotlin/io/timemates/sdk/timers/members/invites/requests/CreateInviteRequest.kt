package io.timemates.sdk.timers.members.invites.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import io.timemates.sdk.timers.types.value.TimerId

public data class CreateInviteRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
    val maxJoinersCount: Count,
) : AuthorizedTimeMatesRequest<CreateInviteRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<CreateInviteRequest>

    override val requestKey: Key get() = Key

    public data class Result(public val inviteCode: InviteCode) : TimeMatesEntity()
}