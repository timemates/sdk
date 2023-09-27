package io.timemates.sdk.authorization.sessions.requests

import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.TimeMatesRequest

public data class GetAuthorizationSessionsRequest(
    val nextPageToken: PageToken?,
    override val accessHash: AccessHash,
) : AuthorizedTimeMatesRequest<Page<Authorization>>() {
    public companion object Key : TimeMatesRequest.Key<GetAuthorizationSessionsRequest>

    override val requestKey: Key
        get() = Key
}