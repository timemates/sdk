package org.timemates.sdk.timers.members.invites.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.members.invites.types.Invite
import org.timemates.sdk.timers.types.value.TimerId

public data class GetInvitesRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
    val pageToken: PageToken?,
) : AuthorizedTimeMatesRequest<Page<Invite>>() {
    public companion object Key : TimeMatesRequest.Key<GetInvitesRequest>

    override val requestKey: Key get() = Key
}