package io.timemates.sdk.authorization.sessions.types.value

import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class ClientName private constructor(public val string: String) {
    public companion object : Factory<ClientName, String>() {
        override fun create(input: String): Result<ClientName> {
            return Result.success(ClientName(input))
        }
    }
}