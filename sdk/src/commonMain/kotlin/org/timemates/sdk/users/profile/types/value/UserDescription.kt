package org.timemates.sdk.users.profile.types.value

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.Factory
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success
import kotlin.jvm.JvmInline

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