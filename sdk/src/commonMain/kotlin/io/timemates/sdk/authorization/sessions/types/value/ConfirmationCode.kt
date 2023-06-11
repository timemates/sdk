package io.timemates.sdk.authorization.sessions.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class ConfirmationCode private constructor(public val string: String) {
    public companion object : Factory<ConfirmationCode, String>() {
        public const val SIZE: Int = 6
        public val PATTERN: Regex = Regex("^[0-9]+$")

        override fun create(input: String): Result<ConfirmationCode> {
            return when {
                input.isBlank() -> Result.failure(CreationFailure.ofBlank())
                input.length != SIZE -> Result.failure(CreationFailure.ofSizeExact(SIZE))
                !input.matches(PATTERN) -> Result.failure(CreationFailure.ofPattern(PATTERN))
                else -> Result.success(ConfirmationCode(input))
            }
        }
    }
}