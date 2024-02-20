package org.timemates.sdk.users.settings.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.annotations.ApiStatus
import org.timemates.sdk.common.annotations.ExperimentalTimeMatesApi
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.users.profile.types.value.EmailAddress

/**
 * This request is not implemented on server yet. Always throws
 * [org.timemates.sdk.common.exceptions.UnsupportedException].
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