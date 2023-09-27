package io.timemates.api.rsocket.users.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.users.types.SerializableUser
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketGetUsersRequest(
    val ids: List<Long>,
) : RSocketRequest<RSocketGetUsersRequest.Result> {
    @Serializable
    data class Result(val list: List<SerializableUser>)
}