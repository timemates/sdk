package org.timemates.api.rsocket.users.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.api.rsocket.users.sdk
import org.timemates.sdk.users.profile.requests.GetUsersRequest
import org.timemates.api.users.requests.GetUsersRequest as RSGetUsersRequest

internal object GetUsersCommand : RSocketCommand<GetUsersRequest, GetUsersRequest.Result> {
    override suspend fun execute(apis: ApiContainer, input: GetUsersRequest): GetUsersRequest.Result {
        return apis.users.getUsers(
            message = RSGetUsersRequest {
                userId = input.users.map { it.long }
            },
        ).let { result ->
            GetUsersRequest.Result(result.users.map { it.sdk() })
        }
    }
}