package io.timemates.sdk.users.profile.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class UserId private constructor(public val long: Long) {
    public companion object : Factory<UserId, Long>() {
        public const val MIN_VALUE: Int = 0

        override fun create(input: Long): Result<UserId> {
            return when {
                input < MIN_VALUE -> Result.failure(CreationFailure.ofMin(MIN_VALUE))
                else -> Result.success(UserId(input))
            }
        }

    }
}