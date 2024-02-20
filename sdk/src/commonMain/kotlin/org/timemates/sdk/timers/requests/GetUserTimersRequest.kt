package org.timemates.sdk.timers.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.timers.types.Timer

public data class GetUserTimersRequest(
    override val accessHash: AccessHash,
    val pageToken: PageToken?,
) : AuthorizedTimeMatesRequest<Page<Timer>>() {
    public companion object Key : TimeMatesRequest.Key<GetUserTimersRequest>

    override val requestKey: Key get() = Key
}