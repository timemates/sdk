package io.timemates.api.rsocket.timers.members.commands

import io.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import io.timemates.api.rsocket.timers.members.invites.commands.timerMemberInvites
import io.timemates.sdk.timers.members.requests.GetMembersRequest
import io.timemates.sdk.timers.members.requests.KickMemberRequest
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.requests.EditTimerRequest
import io.timemates.sdk.timers.requests.GetTimerRequest
import io.timemates.sdk.timers.requests.RemoveTimerRequest

/**
 * RSocket commands related to timer members.
 */
internal fun RSocketCommandsBuilderScope.timerMembers() {
    timerMemberInvites()

    GetTimerMembersCommand associatedWith GetMembersRequest
    KickTimerMemberCommand associatedWith KickMemberRequest
}