package org.timemates.api.rsocket.users.commands

import org.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import org.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import org.timemates.sdk.users.profile.requests.EditProfileRequest
import org.timemates.sdk.users.profile.requests.GetUsersRequest
import org.timemates.sdk.users.settings.requests.EditEmailRequest

/**
 * RSocket commands related to user operations.
 */
@OptIn(ExperimentalTimeMatesApi::class)
internal fun RSocketCommandsBuilderScope.users() {
    EditProfileCommand associatedWith EditProfileRequest
    GetUsersCommand associatedWith GetUsersRequest
    EditEmailCommand associatedWith EditEmailRequest
}