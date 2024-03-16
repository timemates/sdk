package org.timemates.sdk.common.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.minValue
import kotlin.jvm.JvmInline

@JvmInline
public value class Count private constructor(public val int: Int) {
    public companion object : Factory<Count, Int> by factory(
        rules = listOf(ValidationRule.minValue(0)),
        constructor = ::Count,
    )
}