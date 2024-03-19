package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class AuthorizationId private constructor(public val int: Int) {
    public companion object {
        @JvmStatic
        public val factory: Factory<AuthorizationId, Int> = factory(::AuthorizationId)
    }
}