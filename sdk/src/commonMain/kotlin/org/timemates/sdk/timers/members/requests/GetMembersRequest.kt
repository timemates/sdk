package org.timemates.sdk.timers.members.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.value.TimerId
import org.timemates.sdk.users.profile.types.User

public data class GetMembersRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
    val pageToken: PageToken?,
) : AuthorizedTimeMatesRequest<Page<User>>() {
    public companion object Key : TimeMatesRequest.Key<GetMembersRequest>

    override val requestKey: Key get() = Key
}