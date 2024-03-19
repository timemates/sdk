package org.timemates.sdk.authorization.email.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthExact
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class VerificationHash private constructor(public val string: String) {
    public companion object {
        @JvmStatic
        public val REQUIRED_LENGTH: Int = 128

        @JvmStatic
        public val factory: Factory<VerificationHash, String> = factory(
            rules = listOf(
                ValidationRule.lengthExact(REQUIRED_LENGTH),
            ),
            constructor = ::VerificationHash
        )
    }
}