package org.timemates.sdk.users.profile.types.value

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.Factory
import kotlin.jvm.JvmInline

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