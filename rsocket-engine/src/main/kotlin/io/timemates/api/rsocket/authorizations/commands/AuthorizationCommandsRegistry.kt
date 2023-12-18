package io.timemates.api.rsocket.authorizations.commands

import io.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import io.timemates.sdk.authorization.email.requests.ConfigureNewAccountRequest
import io.timemates.sdk.authorization.email.requests.ConfirmAuthorizationRequest
import io.timemates.sdk.authorization.email.requests.StartAuthorizationRequest
import io.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import io.timemates.sdk.authorization.sessions.requests.RenewAuthorizationRequest
import io.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import io.timemates.sdk.common.annotations.ExperimentalTimeMatesApi

/**
 * The commands that is connected to the authorization feature.
 */
@OptIn(ExperimentalTimeMatesApi::class)
internal fun RSocketCommandsBuilderScope.authorizations() {
    StartAuthorizationCommand associatedWith StartAuthorizationRequest
    ConfirmAuthorizationCommand associatedWith ConfirmAuthorizationRequest
    ConfigureNewAccountCommand associatedWith ConfigureNewAccountRequest
    GetAuthorizationSessionsCommand associatedWith GetAuthorizationSessionsRequest
    TerminateCurrentAuthorizationCommand associatedWith TerminateCurrentAuthorizationSessionRequest
    RenewAuthorizationCommand associatedWith RenewAuthorizationRequest
}
