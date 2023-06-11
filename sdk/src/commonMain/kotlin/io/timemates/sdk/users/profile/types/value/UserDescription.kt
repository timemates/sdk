package io.timemates.sdk.users.profile.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

@JvmInline
public value class UserDescription private constructor(public val string: String) {
    public companion object : Factory<UserDescription, String>() {
        /**
         * Size range of the user's short bio.
         */
        public val SIZE_RANGE: IntRange = 3..200

        override fun create(input: String): Result<UserDescription> {
            return when (input.length) {
                !in 0..200 -> failure(CreationFailure.ofSizeRange(SIZE_RANGE))
                else -> success(UserDescription(input))
            }
        }
    }
}