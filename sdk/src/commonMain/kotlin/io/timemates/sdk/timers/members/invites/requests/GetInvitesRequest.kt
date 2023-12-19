package io.timemates.sdk.timers.members.invites.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.members.invites.types.Invite
import io.timemates.sdk.timers.types.value.TimerId

public data class GetInvitesRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
    val pageToken: PageToken?,
) : AuthorizedTimeMatesRequest<Page<Invite>>() {
    public companion object Key : TimeMatesRequest.Key<GetInvitesRequest>

    override val requestKey: Key get() = Key
}