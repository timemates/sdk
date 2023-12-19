package io.timemates.api.rsocket.authorizations.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.sdk
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.api.authorizations.requests.GetAuthorizationsRequest as RSGetAuthorizationsRequest

internal object GetAuthorizationSessionsCommand : RSocketCommand<GetAuthorizationSessionsRequest, Page<Authorization>> {
    override suspend fun execute(apis: ApiContainer, input: GetAuthorizationSessionsRequest): Page<Authorization> {
        return apis.auth.getAuthorizations(
            message = RSGetAuthorizationsRequest(input.nextPageToken?.string.orEmpty()),
            extra = input.accessHash.toExtra(),
        ).let { result ->
            Page(
                results = result.authorizations.map { it.sdk() },
                nextPageToken = result.nextPageToken
                    .takeIf { it.isNotEmpty() }
                    ?.let { PageToken.createOrThrow(it) },
            )
        }
    }
}