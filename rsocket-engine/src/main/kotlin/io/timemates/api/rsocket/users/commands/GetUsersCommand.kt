package io.timemates.api.rsocket.users.commands

import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.users.sdk
import io.timemates.sdk.users.profile.requests.GetUsersRequest
import io.timemates.api.users.requests.GetUsersRequest as RSGetUsersRequest

internal object GetUsersCommand : RSocketCommand<GetUsersRequest, GetUsersRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: GetUsersRequest): GetUsersRequest.Result {
        return apis.users.getUsers(
            message = RSGetUsersRequest(userId = input.users.map { it.long }),
        ).let { result ->
            GetUsersRequest.Result(result.users.map { it.sdk() })
        }
    }
}