package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.Factory
import kotlin.jvm.JvmInline

@JvmInline
public value class AuthorizationId private constructor(public val int: Int) {
    public companion object : Factory<AuthorizationId, Int>() {
        override fun create(input: Int): Result<AuthorizationId> {
            return Result.success(AuthorizationId(input))
        }
    }
}