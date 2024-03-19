package org.timemates.sdk.users.profile.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthRange
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class UserDescription private constructor(public val string: String) {
    public companion object {
        @JvmStatic
        public val LENGTH_RANGE: IntRange = 3..200

        @JvmStatic
        public val factory: Factory<UserDescription, String> = factory(
            rules = listOf(
                ValidationRule.lengthRange(LENGTH_RANGE),
            ),
            constructor = ::UserDescription,
        )
    }
}
