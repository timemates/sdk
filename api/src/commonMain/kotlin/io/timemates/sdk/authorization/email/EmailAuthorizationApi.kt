package io.timemates.sdk.authorization.email

import io.timemates.sdk.authorization.email.requests.ConfirmAuthorizationRequest
import io.timemates.sdk.authorization.email.requests.StartAuthorizationRequest
import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.authorization.sessions.types.value.ConfirmationCode
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.users.profile.types.value.EmailAddress

public class EmailAuthorizationApi(private val engine: TimeMatesRequestsEngine) {
    public suspend fun authorize(emailAddress: EmailAddress): Result<VerificationHash> {
        return engine.execute(StartAuthorizationRequest(emailAddress)).map { it.verificationHash }
    }

    public suspend fun confirm(
        verificationHash: VerificationHash,
        confirmationCode: ConfirmationCode,
    ): Result<ConfirmAuthorizationRequest.Response> {
        return engine.execute(ConfirmAuthorizationRequest(verificationHash, confirmationCode))
    }
}