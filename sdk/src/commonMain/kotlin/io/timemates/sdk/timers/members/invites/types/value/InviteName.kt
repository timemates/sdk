package io.timemates.sdk.timers.members.invites.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class InviteName private constructor(public val string: String) {
    public companion object : Factory<InviteName, String>() {
        override fun create(input: String): Result<InviteName> {
            return when {
                input.isBlank() -> Result.failure(CreationFailure.ofBlank())
                else -> Result.success(InviteName(input))
            }
        }
    }
}