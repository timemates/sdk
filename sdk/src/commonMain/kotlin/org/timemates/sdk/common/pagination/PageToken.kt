package org.timemates.sdk.common.pagination

import org.timemates.sdk.common.constructor.Factory

/**
 * A value class representing a page token.
 * A page token is used to propagate pagination in APIs. It is a string token that
 * identifies a specific page of results, and is used to retrieve the next page
 * of results.
 * @param string The string representation of the page token.
 */
@JvmInline
public value class PageToken private constructor(public val string: String) {
    public companion object : Factory<PageToken, String>() {
        override fun create(input: String): Result<PageToken> {
            return Result.success(PageToken(input))
        }
    }
}