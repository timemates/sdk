package org.timemates.sdk.users.profile.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthRange
import kotlin.jvm.JvmInline

@JvmInline
public value class UserDescription private constructor(public val string: String) {
    public companion object : Factory<UserDescription, String> by factory(
        rules = listOf(
            ValidationRule.lengthRange(UserDescription.LENGTH_RANGE),
        ),
        constructor = ::UserDescription,
    )
}

public val UserDescription.Companion.LENGTH_RANGE: IntRange get() = 3..200
