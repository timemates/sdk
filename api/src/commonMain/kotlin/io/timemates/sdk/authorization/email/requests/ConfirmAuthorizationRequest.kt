package io.timemates.sdk.authorization.email.requests

import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.authorization.sessions.types.value.ConfirmationCode
import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest

public data class ConfirmAuthorizationRequest(
    val verificationHash: VerificationHash,
    val confirmationCode: ConfirmationCode,
) : TimeMatesRequest<ConfirmAuthorizationRequest.Response>() {
    public data class Response(
        val isNewAccount: Boolean,
        val authorization: Authorization?,
    ) : TimeMatesEntity()
}