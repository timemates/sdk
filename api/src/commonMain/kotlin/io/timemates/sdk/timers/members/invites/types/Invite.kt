package io.timemates.sdk.timers.members.invites.types

import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import kotlinx.datetime.Instant

/**
 * Represents an invitation with an invitation code, creation time, and a limit on the number
 * of people who can join. The limit decreases with every join.
 * @param inviteCode The invite code associated with this invitation.
 * @param creationTime The instant when this invitation was created.
 * @param limit The limit on the number of people who can join using this invitation.
 */
public data class Invite(
    val inviteCode: InviteCode,
    val creationTime: Instant,
    val limit: Count,
) : TimeMatesEntity()