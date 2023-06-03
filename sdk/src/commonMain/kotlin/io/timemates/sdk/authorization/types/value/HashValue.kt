package io.timemates.sdk.authorization.types.value

import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class HashValue private constructor(public val string: String) {
    public companion object : Factory<HashValue, String>() {
        override fun create(input: String): Result<HashValue> {
            return Result.success(HashValue(input))
        }
    }
}