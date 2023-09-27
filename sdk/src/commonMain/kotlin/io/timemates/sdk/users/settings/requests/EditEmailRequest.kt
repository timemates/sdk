package io.timemates.sdk.users.settings.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.annotations.ApiStatus
import io.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import io.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.users.profile.types.value.EmailAddress

/**
 * This request is not implemented on server yet. Always throws
 * [io.timemates.sdk.common.exceptions.UnsupportedException].
 *
 * TODO: remove notice once implemented
 */
@ExperimentalTimeMatesApi(status = ApiStatus.IN_PROGRESS)
public data class EditEmailRequest(
    val newEmail: EmailAddress,
    override val accessHash: AccessHash,
) : AuthorizedTimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<EditEmailRequest>

    override val requestKey: Key get() = Key
}