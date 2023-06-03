package io.timemates.sdk.users.profile.requests

import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.users.profile.types.User
import io.timemates.sdk.users.profile.types.value.UserId

public data class GetUsersRequest(
    val users: List<UserId>,
) : TimeMatesRequest<GetUsersRequest.Result>() {
    public data class Result(val users: List<User>) : TimeMatesEntity()
}