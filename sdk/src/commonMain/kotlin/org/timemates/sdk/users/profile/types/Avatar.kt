package org.timemates.sdk.users.profile.types

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.rules.notBlank
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

public sealed interface Avatar {
    @JvmInline
    public value class GravatarId private constructor(public val string: String) : Avatar {
        public companion object {
            @JvmStatic
            public val factory: Factory<GravatarId, String> = factory(
                rules = listOf(ValidationRule.notBlank()),
                constructor = ::GravatarId,
            )
        }
    }
}