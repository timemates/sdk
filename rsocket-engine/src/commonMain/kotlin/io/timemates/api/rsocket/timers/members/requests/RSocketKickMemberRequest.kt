package io.timemates.api.rsocket.timers.members.requests

internal data class RSocketKickMemberRequest(
    val timerId: Long,
    val userId: Long,
)