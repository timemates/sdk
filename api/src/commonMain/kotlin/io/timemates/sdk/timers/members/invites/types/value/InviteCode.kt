package io.timemates.sdk.timers.members.invites.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class InviteCode private constructor(public val string: String) {
    public companion object : Factory<InviteCode, String>() {
        private const val SIZE = 8

        override fun create(input: String): Result<InviteCode> {
            return when {
                input.length == SIZE -> Result.success(InviteCode(input))
                else -> Result.failure(CreationFailure.ofSize(SIZE))
            }
        }
    }
}