package org.timemates.sdk.timers.members.invites.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthExact
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class InviteCode private constructor(public val string: String) {
    public companion object {
        public const val REQUIRED_LENGTH: Int = 8

        @JvmStatic
        public val factory: Factory<InviteCode, String> = factory(
            rules = listOf(ValidationRule.lengthExact(InviteCode.REQUIRED_LENGTH)),
            constructor = ::InviteCode,
        )
    }
}