package io.timemates.sdk.timers.members.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.users.profile.types.User

public data class GetMembersRequest(
    override val accessHash: AccessHash,
    val timerId: TimerId,
    val pageToken: PageToken?,
) : AuthorizedTimeMatesRequest<Page<User>>() {
    public companion object Key : TimeMatesRequest.Key<GetMembersRequest>

    override val requestKey: Key get() = Key
}