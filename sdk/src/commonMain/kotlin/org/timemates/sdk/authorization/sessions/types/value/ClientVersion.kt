package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import kotlin.jvm.JvmInline

@JvmInline
public value class ClientVersion private constructor(public val double: Double) {
    public companion object : Factory<ClientVersion, Double> by factory(::ClientVersion)
}