package org.timemates.sdk.authorization.email.requests

import org.timemates.sdk.authorization.email.types.value.VerificationHash
import org.timemates.sdk.authorization.sessions.types.Authorization
import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.users.profile.types.value.UserDescription
import org.timemates.sdk.users.profile.types.value.UserName

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