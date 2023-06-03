package io.timemates.sdk.common.types.value

import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class PageToken private constructor(public val string: String) {
    public companion object : Factory<PageToken, String>() {
        override fun create(input: String): Result<PageToken> {
            return Result.success(PageToken(input))
        }
    }
}