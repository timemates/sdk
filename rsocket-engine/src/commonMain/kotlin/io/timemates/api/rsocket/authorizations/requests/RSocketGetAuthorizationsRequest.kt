package io.timemates.api.rsocket.authorizations.requests

import io.timemates.api.rsocket.authorizations.types.SerializableAuthorization
import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class RSocketGetAuthorizationsRequest(
    val pageToken: String? = null,
) : RSocketRequest<RSocketGetAuthorizationsRequest.Result> {

    @Serializable
    data class Result(val list: List<SerializableAuthorization>, val nextPageToken: String?)
}