package io.timemates.sdk.authorization.sessions.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.authorization.types.value.HashValue
import io.timemates.sdk.common.annotations.ApiStatus
import io.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest

@ExperimentalTimeMatesApi(status = ApiStatus.IN_PROGRESS)
public data class RenewAuthorizationRequest(
    val refreshHash: HashValue,
) : TimeMatesRequest<RenewAuthorizationRequest.Result>() {
    public companion object Key : TimeMatesRequest.Key<RenewAuthorizationRequest>

    @ExperimentalTimeMatesApi(status = ApiStatus.NEEDS_REVISION)
    public data class Result(
        val accessHash: AccessHash,
    ) : TimeMatesEntity()

    override val requestKey: Key get() = Key
}