package io.timemates.api.rsocket.timers.members.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.members.requests.RSocketGetMembersListRequest
import io.timemates.api.rsocket.timers.requests.RSocketGetUserTimersRequest
import io.timemates.api.rsocket.timers.types.SerializableTimer
import io.timemates.api.rsocket.timers.types.sdk
import io.timemates.api.rsocket.users.types.SerializableUser
import io.timemates.api.rsocket.users.types.sdk
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.timers.members.requests.GetMembersRequest
import io.timemates.sdk.timers.requests.GetUserTimersRequest
import io.timemates.sdk.timers.types.Timer
import io.timemates.sdk.users.profile.types.User

internal object GetTimerMembersCommand : RSocketCommand<GetMembersRequest, Page<User>> {
    override suspend fun execute(rSocket: RSocket, input: GetMembersRequest): Page<User> {
        return rSocket.requestResponse(
            route = "timers.user.list",
            data = RSocketGetMembersListRequest(
                timerId = input.timerId.long,
                pageToken = input.pageToken?.string,
            ),
            accessHash = input.accessHash.string,
        ).let { result ->
            Page(
                results = result.list.map(SerializableUser::sdk),
                nextPageToken = result.nextPageToken?.let { PageToken.createOrThrow(it) },
            )
        }
    }
}