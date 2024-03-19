package org.timemates.sdk.authorization.types.value

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

@JvmInline
public value class HashValue private constructor(public val string: String) {
    public companion object {
        @JvmStatic
        public val factory: Factory<HashValue, String> = factory(constructor = ::HashValue)
    }
}