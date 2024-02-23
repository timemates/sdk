package org.timemates.sdk.timers.types.value

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.Factory
import kotlin.jvm.JvmInline

@JvmInline
public value class TimerDescription private constructor(public val string: String) {
    public companion object : Factory<TimerDescription, String>() {
        /**
         * Size range of the timer's name.
         */
        public val SIZE_RANGE: IntRange = 0..500

        override fun create(input: String): Result<TimerDescription> {
            return when (input.length) {
                !in SIZE_RANGE -> Result.failure(CreationFailure.ofSizeRange(SIZE_RANGE))
                else -> Result.success(TimerDescription(input))
            }
        }
    }
}