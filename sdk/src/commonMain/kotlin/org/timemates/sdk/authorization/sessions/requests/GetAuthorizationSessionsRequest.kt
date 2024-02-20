package org.timemates.sdk.authorization.sessions.requests

import org.timemates.sdk.authorization.sessions.types.Authorization
import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.TimeMatesRequest

public data class GetAuthorizationSessionsRequest(
    val nextPageToken: PageToken?,
    override val accessHash: AccessHash,
) : AuthorizedTimeMatesRequest<Page<Authorization>>() {
    public companion object Key : TimeMatesRequest.Key<GetAuthorizationSessionsRequest>

    override val requestKey: Key
        get() = Key
}