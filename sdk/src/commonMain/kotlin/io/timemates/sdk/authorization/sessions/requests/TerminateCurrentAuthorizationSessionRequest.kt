package io.timemates.sdk.authorization.sessions.requests

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.TimeMatesRequest

public data class TerminateCurrentAuthorizationSessionRequest(
    val accessHash: AccessHash
) : TimeMatesRequest<Empty>()