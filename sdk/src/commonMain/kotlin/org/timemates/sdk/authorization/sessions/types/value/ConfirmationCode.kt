package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.Factory
import kotlin.jvm.JvmInline

@JvmInline
public value class ConfirmationCode private constructor(public val string: String) {
    public companion object : Factory<ConfirmationCode, String>() {
        public const val SIZE: Int = 8

        override fun create(input: String): Result<ConfirmationCode> {
            return when {
                input.isBlank() -> Result.failure(CreationFailure.ofBlank())
                input.length != SIZE -> Result.failure(CreationFailure.ofSizeExact(SIZE))
                else -> Result.success(ConfirmationCode(input))
            }
        }
    }
}
