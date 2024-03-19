package org.timemates.sdk.users.profile.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.minValue
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class UserId private constructor(public val long: Long) {
    public companion object {
        public const val MIN_VALUE: Long = 0

        @JvmStatic
        public val factory: Factory<UserId, Long> = factory(
            rules = listOf(ValidationRule.minValue(MIN_VALUE)),
            constructor = ::UserId,
        )
    }
}