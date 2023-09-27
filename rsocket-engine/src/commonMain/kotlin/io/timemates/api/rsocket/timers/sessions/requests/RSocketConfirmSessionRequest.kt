package io.timemates.api.rsocket.timers.sessions.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data object RSocketConfirmSessionRequest : RSocketRequest<Unit>