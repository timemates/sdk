package io.timemates.api.rsocket.timers.members.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.users.sdk
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.timers.members.requests.GetMembersRequest
import io.timemates.sdk.users.profile.types.User
import io.timemates.api.timers.members.requests.GetMembersRequest as RSGetMembersRequest

internal object GetTimerMembersCommand : RSocketCommand<GetMembersRequest, Page<User>> {
    override suspend fun execute(apis: ApiContainer, input: GetMembersRequest): Page<User> {
        return apis.timers.getMembers(
            message = RSGetMembersRequest(
                timerId = input.timerId.long,
                nextPageToken = input.pageToken?.string.orEmpty(),
            ),
            extra = input.accessHash.toExtra(),
        ).let { result ->
            Page(
                results = result.users.map { it.sdk() },
                nextPageToken = result.nextPageToken
                    .takeIf { it.isNotEmpty() }
                    ?.let { PageToken.createOrThrow(it) },
            )
        }
    }
}