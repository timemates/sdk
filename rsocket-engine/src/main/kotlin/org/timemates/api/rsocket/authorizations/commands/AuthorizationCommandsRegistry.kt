package org.timemates.api.rsocket.authorizations.commands

import org.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import org.timemates.sdk.authorization.email.requests.ConfigureNewAccountRequest
import org.timemates.sdk.authorization.email.requests.ConfirmAuthorizationRequest
import org.timemates.sdk.authorization.email.requests.StartAuthorizationRequest
import org.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import org.timemates.sdk.authorization.sessions.requests.RenewAuthorizationRequest
import org.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import org.timemates.sdk.common.annotations.ExperimentalTimeMatesApi

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
