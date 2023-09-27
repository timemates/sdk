package io.timemates.sdk.timers.members.invites.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import io.timemates.sdk.timers.types.value.TimerId

public data class RemoveInviteRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
    val inviteCode: InviteCode,
) : AuthorizedTimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<RemoveInviteRequest>

    override val requestKey: Key get() = Key
}