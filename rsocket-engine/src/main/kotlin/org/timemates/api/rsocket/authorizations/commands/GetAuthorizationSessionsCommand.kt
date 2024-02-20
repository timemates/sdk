package org.timemates.api.rsocket.authorizations.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.sdk
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import org.timemates.sdk.authorization.sessions.types.Authorization
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.api.authorizations.requests.GetAuthorizationsRequest as RSGetAuthorizationsRequest

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