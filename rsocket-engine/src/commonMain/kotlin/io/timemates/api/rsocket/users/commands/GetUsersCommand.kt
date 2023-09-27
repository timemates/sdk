package io.timemates.api.rsocket.users.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.api.rsocket.users.types.SerializableUser
import io.timemates.api.rsocket.users.types.sdk
import io.timemates.sdk.users.profile.requests.GetUsersRequest
import io.timemates.api.rsocket.users.requests.GetUsersRequest as RSocketGetUsersRequest

internal object GetUsersCommand : RSocketCommand<GetUsersRequest, GetUsersRequest.Result> {
    override suspend fun execute(rSocket: RSocket, input: GetUsersRequest): GetUsersRequest.Result {
        return rSocket.requestResponse(
            route = "users.profile.list",
            data = RSocketGetUsersRequest(ids = input.users.map { it.long })
        ).let { result ->
            GetUsersRequest.Result(result.list.map(SerializableUser::sdk))
        }
    }
}