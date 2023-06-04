package io.timemates.sdk.files.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class FileName private constructor(public val string: String) {
    public companion object : Factory<FileName, String>() {
        override fun create(input: String): Result<FileName> {
            return when {
                input.isBlank() -> Result.failure(CreationFailure.ofBlank())
                else -> Result.success(FileName(input))
            }
        }
    }
}