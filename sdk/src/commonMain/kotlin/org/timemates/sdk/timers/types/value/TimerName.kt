package org.timemates.sdk.timers.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthRange
import kotlin.jvm.JvmInline

@JvmInline
public value class TimerName private constructor(public val string: String) {
    public companion object : Factory<TimerName, String> by factory(
        rules = listOf(
            ValidationRule.lengthRange(TimerName.LENGTH_RANGE),
        ),
        constructor = ::TimerName,
    )
}

public val TimerName.Companion.LENGTH_RANGE: IntRange get() = 3..50