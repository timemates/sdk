package io.timemates.api.rsocket.users.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.ApiContainer
import io.timemates.api.rsocket.authorizations.toExtra
import io.timemates.api.rsocket.common.commands.RSocketCommand
import io.timemates.api.rsocket.common.ext.requestResponse
import io.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.users.settings.requests.EditEmailRequest
import io.timemates.api.users.requests.EditEmailRequest as RSEditEmailRequest

@OptIn(ExperimentalTimeMatesApi::class)
internal object EditEmailCommand : RSocketCommand<EditEmailRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: EditEmailRequest): Empty {
        return apis.users.setEmail(
            message = RSEditEmailRequest(email = input.newEmail.string),
            extra = input.accessHash.toExtra()
        ).let { _ -> Empty }
    }
}