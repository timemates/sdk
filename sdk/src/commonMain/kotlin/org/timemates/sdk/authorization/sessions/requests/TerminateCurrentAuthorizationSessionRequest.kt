package org.timemates.sdk.authorization.sessions.requests

import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.common.types.AuthorizedTimeMatesRequest
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.TimeMatesRequest

public data class TerminateCurrentAuthorizationSessionRequest(
    override val accessHash: AccessHash,
) : AuthorizedTimeMatesRequest<Empty>() {
    public companion object Key : TimeMatesRequest.Key<TerminateCurrentAuthorizationSessionRequest>

    override val requestKey: Key get() = Key
}