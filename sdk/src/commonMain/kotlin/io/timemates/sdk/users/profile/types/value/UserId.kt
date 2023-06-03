package io.timemates.sdk.users.profile.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class UserId private constructor(public val long: Long) {
    public companion object : Factory<UserId, Long>() {
        override fun create(input: Long): Result<UserId> {
            return when {
                input < 0 -> Result.failure(CreationFailure.ofMin(0))
                else -> Result.success(UserId(input))
            }
        }

    }
}