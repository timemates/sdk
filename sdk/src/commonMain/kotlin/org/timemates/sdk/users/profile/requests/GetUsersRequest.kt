package org.timemates.sdk.users.profile.requests

import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.users.profile.types.User
import org.timemates.sdk.users.profile.types.value.UserId

public data class GetUsersRequest(
    val users: List<UserId>,
) : TimeMatesRequest<GetUsersRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<GetUsersRequest>

    public data class Result(val users: List<User>) : TimeMatesEntity()

    override val requestKey: Key get() = Key
}