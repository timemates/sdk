package io.timemates.api.rsocket.timers.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.timers.types.SerializableTimer
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketGetUserTimersRequest(
    val pageToken: String? = null,
) : RSocketRequest<RSocketGetUserTimersRequest.Result> {
    @Serializable
    data class Result(
        val nextPageToken: String? = null,
        val list: List<SerializableTimer>,
    )
}