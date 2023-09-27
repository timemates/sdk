package io.timemates.api.rsocket.authorizations.requests

import io.timemates.api.rsocket.authorizations.types.SerializableAuthorization
import io.timemates.api.rsocket.common.markers.RSocketRequest
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAuthorizationsRequest(
    val pageToken: String? = null,
) : RSocketRequest<GetAuthorizationsRequest.Result> {

    @Serializable
    data class Result(val list: List<SerializableAuthorization>, val nextPageToken: String?)
}