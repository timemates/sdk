package org.timemates.sdk.users.profile.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthRange
import org.timemates.sdk.common.constructor.rules.matchesPattern
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class EmailAddress private constructor(public val string: String) {
    public companion object {
        @JvmStatic
        public val LENGTH_RANGE: IntRange = 5..200

        @JvmStatic
        public val EMAIL_PATTERN: Regex = Regex(
            buildString {
                append("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}")
                append("\\@")
                append("[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}")
                append("(")
                append("\\.")
                append("[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}")
                append(")+")
            }
        )

        @JvmStatic
        public val factory: Factory<EmailAddress, String> = factory(
            rules = listOf(
                ValidationRule.lengthRange(LENGTH_RANGE),
                ValidationRule.matchesPattern(EMAIL_PATTERN),
            ),
            constructor = ::EmailAddress,
        )
    }
}