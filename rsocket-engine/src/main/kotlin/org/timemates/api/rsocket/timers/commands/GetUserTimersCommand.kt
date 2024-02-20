package org.timemates.api.rsocket.timers.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.rsocket.timers.sdk
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.sdk.timers.requests.GetUserTimersRequest
import org.timemates.sdk.timers.types.Timer
import org.timemates.api.timers.requests.GetTimersRequest as RSGetTimersRequest

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