package org.timemates.sdk.common.pagination

import org.timemates.sdk.common.constructor.Factory
import org.timemates.sdk.common.constructor.factory
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic

/**
 * A value class representing a page token.
 * A page token is used to propagate pagination in APIs. It is a string token that
 * identifies a specific page of results, and is used to retrieve the next page
 * of results.
 * @param string The string representation of the page token.
 */
@JvmInline
public value class PageToken private constructor(public val string: String) {
    public companion object {
        @JvmStatic
        public val factory: Factory<PageToken, String> = factory(::PageToken)
    }
}