package org.timemates.sdk.authorization.sessions.requests

import org.timemates.sdk.authorization.sessions.types.Authorization
import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.authorization.types.value.HashValue
import org.timemates.sdk.common.annotations.ApiStatus
import org.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest

@ExperimentalTimeMatesApi(status = ApiStatus.IN_PROGRESS)
public data class RenewAuthorizationRequest(
    val refreshHash: HashValue,
) : TimeMatesRequest<RenewAuthorizationRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<RenewAuthorizationRequest>

    @ExperimentalTimeMatesApi(status = ApiStatus.NEEDS_REVISION)
    public data class Result(
        val authorization: Authorization,
    ) : TimeMatesEntity()

    override val requestKey: Key get() = Key
}