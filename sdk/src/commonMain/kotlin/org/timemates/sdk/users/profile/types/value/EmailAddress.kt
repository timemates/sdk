package org.timemates.sdk.users.profile.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.CreationFailure

@JvmInline
public value class EmailAddress private constructor(public val string: String) {
    public companion object : Factory<EmailAddress, String>() {
        public val SIZE_RANGE: IntRange = 5..200

        public val PATTERN: Regex = Regex(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        )

        override fun create(input: String): Result<EmailAddress> {
            return when {
                input.length !in SIZE_RANGE ->
                    Result.failure(CreationFailure.ofSizeRange(SIZE_RANGE))
                !PATTERN.matches(input) ->
                    Result.failure(CreationFailure.ofPattern(PATTERN))

                else -> Result.success(EmailAddress(input))
            }
        }
    }
}