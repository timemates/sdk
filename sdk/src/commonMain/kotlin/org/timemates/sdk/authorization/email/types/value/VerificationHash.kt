package org.timemates.sdk.authorization.email.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.CreationFailure
import kotlin.jvm.JvmInline

@JvmInline
public value class VerificationHash private constructor(public val string: String) {
    public companion object : Factory<VerificationHash, String>() {
        public const val SIZE: Int = 128

        override fun create(input: String): Result<VerificationHash> {
            return when (input.length) {
                SIZE -> Result.success(VerificationHash(input))
                else -> Result.failure(CreationFailure.ofSizeExact(SIZE))
            }
        }
    }
}