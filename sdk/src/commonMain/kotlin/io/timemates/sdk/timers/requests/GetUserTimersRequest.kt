package io.timemates.sdk.timers.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.Timer

public data class GetUserTimersRequest(
    override val accessHash: AccessHash,
    val pageToken: PageToken?,
) : AuthorizedTimeMatesRequest<Page<Timer>>() {
    public companion object Key : TimeMatesRequest.Key<GetUserTimersRequest>

    override val requestKey: Key get() = Key
}