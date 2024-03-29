package org.timemates.sdk.timers.members.invites.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.notBlank
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class InviteName private constructor(public val string: String) {
    public companion object {
        @JvmStatic
        public val factory: Factory<InviteName, String> = factory(
            rules = listOf(
                ValidationRule.notBlank(),
            ),
            constructor = ::InviteName
        )
    }
}