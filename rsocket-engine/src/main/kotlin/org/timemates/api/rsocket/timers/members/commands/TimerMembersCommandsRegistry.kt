package org.timemates.api.rsocket.timers.members.commands

import org.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import org.timemates.api.rsocket.timers.members.invites.commands.timerMemberInvites
import org.timemates.sdk.timers.members.requests.GetMembersRequest
import org.timemates.sdk.timers.members.requests.KickMemberRequest
import org.timemates.sdk.timers.requests.CreateTimerRequest
import org.timemates.sdk.timers.requests.EditTimerRequest
import org.timemates.sdk.timers.requests.GetTimerRequest
import org.timemates.sdk.timers.requests.RemoveTimerRequest

/**
 * RSocket commands related to timer members.
 */
internal fun RSocketCommandsBuilderScope.timerMembers() {
    timerMemberInvites()

    GetTimerMembersCommand associatedWith GetMembersRequest
    KickTimerMemberCommand associatedWith KickMemberRequest
}