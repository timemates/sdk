package io.timemates.sdk.authorization.email.requests

import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.users.profile.types.value.EmailAddress
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