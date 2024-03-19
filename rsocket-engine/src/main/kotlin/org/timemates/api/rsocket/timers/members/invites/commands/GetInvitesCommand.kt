package org.timemates.api.rsocket.timers.members.invites.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.rsocket.timers.sdk
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.sdk.timers.members.invites.requests.GetInvitesRequest
import org.timemates.sdk.timers.members.invites.types.Invite
import org.timemates.api.timers.members.invites.requests.GetInvitesRequest as RSGetInvitesRequest

internal object GetInvitesCommand : RSocketCommand<GetInvitesRequest, Page<Invite>> {
    override suspend fun execute(apis: ApiContainer, input: GetInvitesRequest): Page<Invite> {
        return apis.timers.getInvites(
            message = RSGetInvitesRequest {
                timerId = input.timerId.long
                nextPageToken = input.pageToken?.string.orEmpty()
            },
            extra = input.accessHash.toExtra(),
        ).let { result ->
            Page(
                results = result.invites.map { it.sdk() },
                nextPageToken = result.nextPageToken
                    .takeIf { it.isNotEmpty() }
                    ?.let { PageToken.factory.createOrThrow(it) },
            )
        }
    }
}