package io.timemates.api.rsocket.authorizations.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.authorizations.requests.RSocketGetAuthorizationsRequest
import io.timemates.api.rsocket.authorizations.types.SerializableAuthorization
import io.timemates.api.rsocket.authorizations.types.sdk
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken

internal object GetAuthorizationSessionsCommand : RSocketCommand<GetAuthorizationSessionsRequest, Page<Authorization>> {
    override suspend fun execute(rSocket: RSocket, input: GetAuthorizationSessionsRequest): Page<Authorization> {
        return rSocket.requestResponse(
            route = "authorizations.list",
            data = RSocketGetAuthorizationsRequest(input.nextPageToken?.string),
            accessHash = input.accessHash.string,
        ).let { result ->
            Page(
                results = result.list.map(SerializableAuthorization::sdk),
                nextPageToken = result.nextPageToken?.let { PageToken.createOrThrow(it) }
            )
        }
    }
}