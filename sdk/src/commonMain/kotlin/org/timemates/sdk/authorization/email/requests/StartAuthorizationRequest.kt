package org.timemates.sdk.authorization.email.requests

import org.timemates.sdk.authorization.email.types.value.VerificationHash
import org.timemates.sdk.authorization.sessions.types.Authorization
import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.common.types.value.Count
import org.timemates.sdk.users.profile.types.value.EmailAddress
import kotlinx.datetime.Instant

public data class StartAuthorizationRequest(
    val emailAddress: EmailAddress,
    val metadata: Authorization.Metadata,
) : TimeMatesRequest<StartAuthorizationRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<StartAuthorizationRequest>

    public data class Result(
        val verificationHash: VerificationHash,
        val attempts: Count,
        val expiresAt: Instant,
    ) : TimeMatesEntity()

    override val requestKey: Key get() = Key
}