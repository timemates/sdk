package io.timemates.sdk.authorization.email.types.value

import io.timemates.sdk.common.constructor.Factory
import io.timemates.sdk.common.constructor.CreationFailure


@JvmInline
public value class VerificationHash private constructor(public val string: String) {
    public companion object : Factory<VerificationHash, String>() {
        private const val SIZE: Int = 128

        override fun create(input: String): Result<VerificationHash> {
            return when (input.length) {
                SIZE -> Result.success(VerificationHash(input))
                else -> Result.failure(CreationFailure.ofSize(SIZE))
            }
        }
    }
}