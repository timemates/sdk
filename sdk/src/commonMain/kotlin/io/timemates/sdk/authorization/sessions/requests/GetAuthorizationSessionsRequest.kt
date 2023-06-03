package io.timemates.sdk.authorization.sessions.requests

import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.common.types.value.PageToken

public data class GetAuthorizationSessionsRequest(
    val nextPageToken: PageToken?,
    val accessHash: AccessHash,
) : TimeMatesRequest<GetAuthorizationSessionsRequest.Result>() {
    public data class Result(
        val authorizations: List<Authorization>,
        val nextPageToken: PageToken?
    ) : TimeMatesEntity()
}