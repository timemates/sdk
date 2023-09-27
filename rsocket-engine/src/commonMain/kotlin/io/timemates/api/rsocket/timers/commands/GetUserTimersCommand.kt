package io.timemates.api.rsocket.timers.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.timers.requests.RSocketGetUserTimersRequest
import io.timemates.api.rsocket.timers.types.SerializableTimer
import io.timemates.api.rsocket.timers.types.sdk
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.timers.requests.GetUserTimersRequest
import io.timemates.sdk.timers.types.Timer

internal object GetUserTimersCommand : RSocketCommand<GetUserTimersRequest, Page<Timer>> {
    override suspend fun execute(rSocket: RSocket, input: GetUserTimersRequest): Page<Timer> {
        return rSocket.requestResponse(
            route = "timers.user.list",
            data = RSocketGetUserTimersRequest(
                pageToken = input.pageToken?.string,
            ),
            accessHash = input.accessHash.string,
        ).let { result ->
            Page(
                results = result.list.map(SerializableTimer::sdk),
                nextPageToken = result.nextPageToken?.let { PageToken.createOrThrow(it) },
            )
        }
    }
}