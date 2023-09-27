package io.timemates.sdk.authorization.sessions.types.value

import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class ApplicationName private constructor(public val string: String) {
    public companion object : Factory<ApplicationName, String>() {
        override fun create(input: String): Result<ApplicationName> {
            return Result.success(ApplicationName(input))
        }
    }
}