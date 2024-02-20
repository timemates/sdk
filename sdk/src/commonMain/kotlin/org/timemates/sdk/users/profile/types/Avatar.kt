package org.timemates.sdk.users.profile.types

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.Factory

public sealed interface Avatar {
    @JvmInline
    public value class GravatarId private constructor(public val string: String) : Avatar {
        public companion object : Factory<GravatarId, String>() {
            override fun create(input: String): Result<GravatarId> {
                return when {
                    input.isBlank() -> Result.failure(CreationFailure.ofBlank())
                    else -> Result.success(GravatarId(input))
                }
            }
        }
    }
}