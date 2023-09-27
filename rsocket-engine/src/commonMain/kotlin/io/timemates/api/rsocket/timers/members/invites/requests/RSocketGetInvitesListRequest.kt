package io.timemates.api.rsocket.timers.members.invites.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.timers.members.invites.types.SerializableInvite
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketGetInvitesListRequest(
    val timerId: Long,
    val pageToken: String? = null,
) : RSocketRequest<RSocketGetInvitesListRequest.Result> {
    @Serializable
    data class Result(
        val invites: List<SerializableInvite>,
        val nextPageToken: String? = null,
    )
}