package io.timemates.sdk.timers.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class TimerId private constructor(public val long: Long) {
    public companion object : Factory<TimerId, Long>() {
        override fun create(input: Long): Result<TimerId> {
            return when {
                input < 0 -> Result.failure(CreationFailure.ofMin(0))
                else -> Result.success(TimerId(input))
            }
        }
    }
}