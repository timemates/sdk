package io.timemates.api.rsocket.timers.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.timers.sdk
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.timers.requests.GetUserTimersRequest
import io.timemates.sdk.timers.types.Timer
import io.timemates.api.timers.requests.GetTimersRequest as RSGetTimersRequest

internal object GetUserTimersCommand : RSocketCommand<GetUserTimersRequest, Page<Timer>> {
    override suspend fun execute(apis: ApiContainer, input: GetUserTimersRequest): Page<Timer> {
        return apis.timers.getTimers(
            message = RSGetTimersRequest(
                nextPageToken = input.pageToken?.string.orEmpty(),
            ),
            extra = input.accessHash.toExtra(),
        ).let { result ->
            Page(
                results = result.timers.map { it.sdk() },
                nextPageToken = result.nextPageToken
                    .takeIf { it.isNotEmpty() }
                    ?.let { PageToken.createOrThrow(it) },
            )
        }
    }
}