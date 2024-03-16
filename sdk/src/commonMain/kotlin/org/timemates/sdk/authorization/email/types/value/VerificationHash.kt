package org.timemates.sdk.authorization.email.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthExact
import kotlin.jvm.JvmInline

@JvmInline
public value class VerificationHash private constructor(public val string: String) {
    public companion object : Factory<VerificationHash, String> by factory(
        rules = listOf(
            ValidationRule.lengthExact(128),
        ),
        constructor = ::VerificationHash
    )
}