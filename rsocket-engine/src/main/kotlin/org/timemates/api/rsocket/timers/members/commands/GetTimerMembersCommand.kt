package org.timemates.api.rsocket.timers.members.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.rsocket.users.sdk
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.sdk.timers.members.requests.GetMembersRequest
import org.timemates.sdk.users.profile.types.User
import org.timemates.api.timers.members.requests.GetMembersRequest as RSGetMembersRequest

internal object GetTimerMembersCommand : RSocketCommand<GetMembersRequest, Page<User>> {
    override suspend fun execute(apis: ApiContainer, input: GetMembersRequest): Page<User> {
        return apis.timers.getMembers(
            message = RSGetMembersRequest {
                timerId = input.timerId.long
                nextPageToken = input.pageToken?.string.orEmpty()
            },
            extra = input.accessHash.toExtra(),
        ).let { result ->
            Page(
                results = result.users.map { it.sdk() },
                nextPageToken = result.nextPageToken
                    .takeIf { it.isNotEmpty() }
                    ?.let { PageToken.factory.createOrThrow(it) },
            )
        }
    }
}