package io.timemates.api.rsocket.users.requests

import io.timemates.api.rsocket.common.markers.RSocketRequest
import io.timemates.api.rsocket.users.types.SerializableAvatar
import kotlinx.serialization.Serializable

@Serializable
internal data class EditUserProfileRequest(
    val avatar: SerializableAvatar?,
    val name: String?,
    val description: String?,
) : RSocketRequest<Unit>