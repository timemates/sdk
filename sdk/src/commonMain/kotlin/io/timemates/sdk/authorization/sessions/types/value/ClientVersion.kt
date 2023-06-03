package io.timemates.sdk.authorization.sessions.types.value

import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class ClientVersion private constructor(public val string: String) {
    public companion object : Factory<ClientVersion, String>() {
        override fun create(input: String): Result<ClientVersion> {
            return Result.success(ClientVersion(input))
        }
    }
}