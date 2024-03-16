package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthExact
import org.timemates.sdk.common.constructor.rules.notBlank
import kotlin.jvm.JvmInline

@JvmInline
public value class ConfirmationCode private constructor(public val string: String) {
    public companion object : Factory<ConfirmationCode, String> by factory(
        rules = listOf(
            ValidationRule.notBlank(),
            ValidationRule.lengthExact(8),
        ),
        constructor = ::ConfirmationCode,
    )
}
