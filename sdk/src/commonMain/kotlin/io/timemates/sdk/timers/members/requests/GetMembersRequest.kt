package io.timemates.sdk.timers.members.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.users.profile.types.User

public data class GetMembersRequest(
    val accessHash: AccessHash,
    val timerId: TimerId,
    val pageToken: PageToken?,
) : TimeMatesRequest<Page<User>>()