package org.timemates.sdk.authorization.sessions.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import kotlin.jvm.JvmInline

@JvmInline
public value class ApplicationName private constructor(public val string: String) {
    public companion object : Factory<ApplicationName, String> by factory(::ApplicationName)
}