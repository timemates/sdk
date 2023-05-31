package io.timemates.sdk.authorization.sessions.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class ConfirmationCode private constructor(public val value: String) {
    public companion object : Factory<ConfirmationCode, String>() {
        private const val SIZE = 6
        private val PATTERN = Regex("^[0-9]+$")

        override fun create(input: String): Result<ConfirmationCode> {
            return when {
                input.isBlank() -> Result.failure(CreationFailure.ofBlank())
                input.length != SIZE -> Result.failure(CreationFailure.ofSize(SIZE))
                !input.matches(PATTERN) -> Result.failure(CreationFailure.ofPattern(PATTERN))
                else -> Result.success(ConfirmationCode(input))
            }
        }
    }
}