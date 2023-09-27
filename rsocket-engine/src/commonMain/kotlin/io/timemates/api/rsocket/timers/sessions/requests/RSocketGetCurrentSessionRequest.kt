package io.timemates.api.rsocket.timers.sessions.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.timers.types.SerializableTimer
import kotlinx.serialization.Serializable

@Serializable
internal data object RSocketGetCurrentSessionRequest : RSocketRequest<RSocketGetCurrentSessionRequest.Result> {
    @Serializable
    data class Result(val timer: SerializableTimer)
}