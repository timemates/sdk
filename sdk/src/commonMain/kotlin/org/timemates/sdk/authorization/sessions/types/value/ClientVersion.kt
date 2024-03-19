package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class ClientVersion private constructor(public val double: Double) {
    public companion object {
        @JvmStatic
        public val factory: Factory<ClientVersion, Double> = factory(::ClientVersion)
    }
}