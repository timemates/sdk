package io.timemates.sdk.timers.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class TimerDescription private constructor(public val string: String) {
    public companion object : Factory<TimerDescription, String>() {
        /**
         * Size range of the timer's name.
         */
        private val SIZE = 3..500

        override fun create(input: String): Result<TimerDescription> {
            return when (input.length) {
                !in SIZE -> Result.failure(CreationFailure.ofSize(SIZE))
                else -> Result.success(TimerDescription(input))
            }
        }
    }
}