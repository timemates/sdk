package org.timemates.sdk.timers.members.invites.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.common.types.value.Count
import org.timemates.sdk.timers.members.invites.types.value.InviteCode
import org.timemates.sdk.timers.types.value.TimerId

public data class CreateInviteRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
    val maxJoinersCount: Count,
) : AuthorizedTimeMatesRequest<CreateInviteRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<CreateInviteRequest>

    override val requestKey: Key get() = Key

    public data class Result(public val inviteCode: InviteCode) : TimeMatesEntity()
}