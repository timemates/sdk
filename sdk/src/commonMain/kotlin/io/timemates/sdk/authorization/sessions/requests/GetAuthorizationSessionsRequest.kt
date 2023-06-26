package io.timemates.sdk.authorization.sessions.requests

import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest

public data class GetAuthorizationSessionsRequest(
    val nextPageToken: PageToken?,
    val accessHash: AccessHash,
) : TimeMatesRequest<Page<Authorization>>()