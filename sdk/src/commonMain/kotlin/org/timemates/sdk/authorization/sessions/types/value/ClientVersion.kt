package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.Factory
import kotlin.jvm.JvmInline

@JvmInline
public value class ClientVersion private constructor(public val double: Double) {
    public companion object : Factory<ClientVersion, Double>() {
        override fun create(input: Double): Result<ClientVersion> {
            return Result.success(ClientVersion(input))
        }
    }
}