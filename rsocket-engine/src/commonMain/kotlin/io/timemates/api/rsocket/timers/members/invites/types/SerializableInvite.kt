package io.timemates.api.rsocket.timers.members.invites.types

import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.timers.members.invites.types.Invite
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
internal data class SerializableInvite(
    val timerId: Long,
    val code: String,
    val creationTime: Long,
    val limit: Int,
)

internal fun SerializableInvite.sdk(): Invite {
    return Invite(
        inviteCode = InviteCode.createOrThrow(code),
        creationTime = Instant.fromEpochMilliseconds(creationTime),
        limit = Count.createOrThrow(limit),
    )
}