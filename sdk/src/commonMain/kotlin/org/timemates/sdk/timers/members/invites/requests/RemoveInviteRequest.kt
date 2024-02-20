package org.timemates.sdk.timers.members.invites.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.members.invites.types.value.InviteCode
import org.timemates.sdk.timers.types.value.TimerId

public data class RemoveInviteRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
    val inviteCode: InviteCode,
) : AuthorizedTimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<RemoveInviteRequest>

    override val requestKey: Key get() = Key
}