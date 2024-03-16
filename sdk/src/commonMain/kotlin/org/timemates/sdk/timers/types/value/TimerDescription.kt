package org.timemates.sdk.timers.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthRange
import kotlin.jvm.JvmInline

@JvmInline
public value class TimerDescription private constructor(public val string: String) {
    public companion object : Factory<TimerDescription, String> by factory(
        rules = listOf(
            ValidationRule.lengthRange(0..500),
        ),
        constructor = ::TimerDescription,
    )
}