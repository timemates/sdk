package io.timemates.sdk.timers.members.invites.requests

import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.common.types.value.PageToken
import io.timemates.sdk.timers.members.invites.types.Invite
import io.timemates.sdk.timers.types.value.TimerId

public data class GetInvitesRequest(
    val timerId: TimerId,
    val pageToken: PageToken?,
) : TimeMatesRequest<CreateInviteRequest.Result>() {
    public data class Result(
        val invites: List<Invite>,
        val nextPageToken: PageToken?,
    ) : TimeMatesEntity()
}