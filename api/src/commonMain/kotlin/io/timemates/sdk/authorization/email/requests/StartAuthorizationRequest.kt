package io.timemates.sdk.authorization.email.requests

import io.timemates.sdk.users.profile.types.value.EmailAddress
import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest

public data class StartAuthorizationRequest(
    val emailAddress: EmailAddress
) : TimeMatesRequest<StartAuthorizationRequest.Result>() {
    public data class Result(
        val verificationHash: VerificationHash,
    ) : TimeMatesEntity()
}