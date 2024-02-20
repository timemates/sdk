package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.Factory

@JvmInline
public value class ClientIpAddress private constructor(public val string: String) {
    public companion object : Factory<ClientIpAddress, String>() {
        override fun create(input: String): Result<ClientIpAddress> {
            return Result.success(ClientIpAddress(input))
        }
    }
}