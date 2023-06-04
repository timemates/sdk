package io.timemates.sdk.timers.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.common.types.value.PageToken
import io.timemates.sdk.timers.types.Timer

public data class GetUserTimersRequest(
    val accessHash: AccessHash,
    val pageToken: PageToken,
) : TimeMatesRequest<GetUserTimersRequest.Result>() {
    public data class Result(
        val timers: List<Timer>,
        val nextPageToken: PageToken?,
    ) : TimeMatesEntity()
}