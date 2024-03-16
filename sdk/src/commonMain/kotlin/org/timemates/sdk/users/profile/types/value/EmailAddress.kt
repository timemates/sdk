package org.timemates.sdk.users.profile.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthRange
import org.timemates.sdk.common.constructor.rules.matchesPattern
import kotlin.jvm.JvmInline

@JvmInline
public value class EmailAddress private constructor(public val string: String) {
    public companion object : Factory<EmailAddress, String> by factory(
        rules = listOf(
            ValidationRule.lengthRange(5..200),
            ValidationRule.matchesPattern(EMAIL_PATTERN),
        ),
        constructor = ::EmailAddress,
    )
}

private val EMAIL_PATTERN: Regex = Regex(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"
)