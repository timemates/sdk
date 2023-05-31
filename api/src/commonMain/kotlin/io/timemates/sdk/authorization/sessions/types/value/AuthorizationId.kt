package io.timemates.sdk.authorization.sessions.types.value

import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class AuthorizationId private constructor(public val int: Int) {
    public companion object : Factory<AuthorizationId, Int>() {
        override fun create(input: Int): Result<AuthorizationId> {
            return Result.success(AuthorizationId(input))
        }
    }
}