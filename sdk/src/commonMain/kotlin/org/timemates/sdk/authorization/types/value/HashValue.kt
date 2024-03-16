package org.timemates.sdk.authorization.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import kotlin.jvm.JvmInline

@JvmInline
public value class HashValue private constructor(public val string: String) {
    public companion object : Factory<HashValue, String> by factory(
        constructor = ::HashValue,
    )
}