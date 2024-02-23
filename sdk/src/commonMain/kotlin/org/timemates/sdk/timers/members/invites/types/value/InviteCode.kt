package org.timemates.sdk.timers.members.invites.types.value

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.Factory
import kotlin.jvm.JvmInline

@JvmInline
public value class InviteCode private constructor(public val string: String) {
    public companion object : Factory<InviteCode, String>() {
        public const val SIZE: Int = 8

        override fun create(input: String): Result<InviteCode> {
            return when {
                input.length == SIZE -> Result.success(InviteCode(input))
                else -> Result.failure(CreationFailure.ofSizeExact(SIZE))
            }
        }
    }
}