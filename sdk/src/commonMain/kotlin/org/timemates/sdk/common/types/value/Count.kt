package org.timemates.sdk.common.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.minValue
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class Count private constructor(public val int: Int) {
    public companion object {
        public const val MIN_VALUE: Int = 0

        @JvmStatic
        public val factory: Factory<Count, Int> = factory(
            rules = listOf(ValidationRule.minValue(MIN_VALUE)),
            constructor = ::Count,
        )
    }
}