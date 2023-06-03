package io.timemates.sdk.timers.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class TimerName private constructor(public val string: String) {
    public companion object : Factory<TimerName, String>() {
        /**
         * Size range of the timer's name.
         */
        private val SIZE = 3..50

        override fun create(input: String): Result<TimerName> {
            return when (input.length) {
                !in SIZE -> Result.failure(CreationFailure.ofSize(SIZE))
                else -> Result.success(TimerName(input))
            }
        }
    }
}