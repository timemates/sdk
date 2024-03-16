package org.timemates.sdk.timers.members.invites.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.lengthExact
import kotlin.jvm.JvmInline

@JvmInline
public value class InviteCode private constructor(public val string: String) {
    public companion object : Factory<InviteCode, String> by factory(
        rules = listOf(ValidationRule.lengthExact(InviteCode.REQUIRED_LENGTH)),
        constructor = ::InviteCode,
    )
}

public val InviteCode.Companion.REQUIRED_LENGTH: Int get() = 8