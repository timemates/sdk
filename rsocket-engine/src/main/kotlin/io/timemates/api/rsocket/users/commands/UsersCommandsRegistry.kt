package io.timemates.api.rsocket.users.commands

import io.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import io.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import io.timemates.sdk.users.profile.requests.EditProfileRequest
import io.timemates.sdk.users.profile.requests.GetUsersRequest
import io.timemates.sdk.users.settings.requests.EditEmailRequest

/**
 * RSocket commands related to user operations.
 */
@OptIn(ExperimentalTimeMatesApi::class)
internal fun RSocketCommandsBuilderScope.users() {
    EditProfileCommand associatedWith EditProfileRequest
    GetUsersCommand associatedWith GetUsersRequest
    EditEmailCommand associatedWith EditEmailRequest
}