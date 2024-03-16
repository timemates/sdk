package org.timemates.sdk.timers.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.minValue
import kotlin.jvm.JvmInline

@JvmInline
public value class TimerId private constructor(public val long: Long) {
    public companion object : Factory<TimerId, Long> by factory(
        rules = listOf(
            ValidationRule.minValue(0),
        ),
        constructor = ::TimerId,
    )
}