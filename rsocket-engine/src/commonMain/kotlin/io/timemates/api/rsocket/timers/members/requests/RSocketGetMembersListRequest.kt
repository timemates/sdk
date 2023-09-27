package io.timemates.api.rsocket.timers.members.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.users.types.SerializableUser
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketGetMembersListRequest(
    val timerId: Long,
    val pageToken: String? = null,
) : RSocketRequest<RSocketGetMembersListRequest.Result> {
    @Serializable
    data class Result(
        val list: List<SerializableUser>,
        val nextPageToken: String? = null,
    )
}