package org.timemates.api.rsocket.users.commands

import org.timemates.api.rsocket.ApiContainer
import org.timemates.api.rsocket.authorizations.toExtra
import org.timemates.api.rsocket.common.commands.RSocketCommand
import org.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.users.settings.requests.EditEmailRequest
import org.timemates.api.users.requests.EditEmailRequest as RSEditEmailRequest

@OptIn(ExperimentalTimeMatesApi::class)
internal object EditEmailCommand : RSocketCommand<EditEmailRequest, Empty> {
    override suspend fun execute(apis: ApiContainer, input: EditEmailRequest): Empty {
        return apis.users.setEmail(
            message = RSEditEmailRequest(email = input.newEmail.string),
            extra = input.accessHash.toExtra()
        ).let { _ -> Empty }
    }
}