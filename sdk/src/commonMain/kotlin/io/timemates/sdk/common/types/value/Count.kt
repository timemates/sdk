package io.timemates.sdk.common.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class Count internal constructor(public val int: Int) {
    public companion object : Factory<Count, Int>() {
        override fun create(input: Int): Result<Count> {
            return when {
                input < 0 -> Result.failure(CreationFailure.ofMin(0))
                else -> Result.success(Count(input))
            }
        }
    }
}