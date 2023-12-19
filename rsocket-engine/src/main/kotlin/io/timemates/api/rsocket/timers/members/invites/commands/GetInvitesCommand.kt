package io.timemates.api.rsocket.timers.members.invites.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.timers.sdk
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.timers.members.invites.requests.GetInvitesRequest
import io.timemates.sdk.timers.members.invites.types.Invite
import io.timemates.api.timers.members.invites.requests.GetInvitesRequest as RSGetInvitesRequest

internal object GetInvitesCommand : RSocketCommand<GetInvitesRequest, Page<Invite>> {
    override suspend fun execute(apis: ApiContainer, input: GetInvitesRequest): Page<Invite> {
        return apis.timers.getInvites(
            message = RSGetInvitesRequest(
                timerId = input.timerId.long,
                nextPageToken = input.pageToken?.string.orEmpty(),
            ),
            extra = input.accessHash.toExtra(),
        ).let { result ->
            Page(
                results = result.invites.map { it.sdk() },
                nextPageToken = result.nextPageToken
                    .takeIf { it.isNotEmpty() }
                    ?.let { PageToken.createOrThrow(it) },
            )
        }
    }
}