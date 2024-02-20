package org.timemates.sdk.users.profile.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.CreationFailure

@JvmInline
public value class UserName private constructor(public val string: String) {
    public companion object : Factory<UserName, String>() {
        /**
         * Size range of the user's name.
         */
        public val SIZE_RANGE: IntRange = 3..50

        override fun create(input: String): Result<UserName> {
            return when (input.length) {
                !in SIZE_RANGE -> Result.failure(CreationFailure.ofSizeRange(SIZE_RANGE))
                else -> Result.success(UserName(input))
            }
        }
    }
}