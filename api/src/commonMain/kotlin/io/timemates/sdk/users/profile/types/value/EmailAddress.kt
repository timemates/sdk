package io.timemates.sdk.users.profile.types.value

import io.timemates.sdk.common.constructor.Factory
import io.timemates.sdk.common.constructor.CreationFailure

@JvmInline
public value class EmailAddress private constructor(public val string: String) {
    public companion object : Factory<EmailAddress, String>() {
        private val SIZE: IntRange = 5..200

        private val emailPattern = Regex(
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
                input.length !in SIZE ->
                    Result.failure(CreationFailure.ofSize(SIZE))
                !emailPattern.matches(input) ->
                    Result.failure(CreationFailure.ofPattern(emailPattern))

                else -> Result.success(EmailAddress(input))
            }
        }
    }
}