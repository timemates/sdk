package io.timemates.sdk.authorization.email.requests

import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserName

public data class ConfigureNewAccountRequest(
    val verificationHash: VerificationHash,
    val name: UserName,
    val description: UserDescription?,
) : TimeMatesRequest<ConfigureNewAccountRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<ConfigureNewAccountRequest>

    public data class Result(val authorization: Authorization) : TimeMatesEntity()

    override val requestKey: Key
        get() = Key
}