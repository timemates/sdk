package io.timemates.api.rsocket.timers.members.invites.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.members.invites.requests.RSocketGetInvitesListRequest
import io.timemates.api.rsocket.timers.members.invites.types.SerializableInvite
import io.timemates.api.rsocket.timers.members.invites.types.sdk
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.timers.members.invites.requests.GetInvitesRequest
import io.timemates.sdk.timers.members.invites.types.Invite

internal object GetInvitesCommand : RSocketCommand<GetInvitesRequest, Page<Invite>> {
    override suspend fun execute(rSocket: RSocket, input: GetInvitesRequest): Page<Invite> {
        return rSocket.requestResponse(
            route = "timers.user.list",
            data = RSocketGetInvitesListRequest(
                timerId = input.timerId.long,
                pageToken = input.pageToken?.string,
            ),
            accessHash = input.accessHash.string,
        ).let { result ->
            Page(
                results = result.invites.map(SerializableInvite::sdk),
                nextPageToken = result.nextPageToken?.let { PageToken.createOrThrow(it) },
            )
        }
    }
}