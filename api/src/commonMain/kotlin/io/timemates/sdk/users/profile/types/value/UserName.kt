package io.timemates.sdk.users.profile.types.value

import io.timemates.sdk.common.constructor.Factory
import io.timemates.sdk.common.constructor.CreationFailure

@JvmInline
public value class UserName private constructor(public val string: String) {
    public companion object : Factory<UserName, String>() {
        /**
         * Size range of the user's name.
         */
        private val SIZE = 3..50

        override fun create(input: String): Result<UserName> {
            return when (input.length) {
                !in SIZE -> Result.failure(CreationFailure.ofSize(SIZE))
                else -> Result.success(UserName(input))
            }
        }
    }
}