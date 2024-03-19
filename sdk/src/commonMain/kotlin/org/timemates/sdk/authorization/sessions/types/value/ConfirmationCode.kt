package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthExact
import org.timemates.sdk.common.constructor.rules.notBlank
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class ConfirmationCode private constructor(public val string: String) {
    public companion object {
        @JvmStatic
        public val REQUIRED_LENGTH: Int = 8

        public val factory: Factory<ConfirmationCode, String> = factory(
            rules = listOf(
                ValidationRule.notBlank(),
                ValidationRule.lengthExact(REQUIRED_LENGTH),
            ),
            constructor = ::ConfirmationCode,
        )
    }
}
