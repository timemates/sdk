package org.timemates.api.rsocket.timers.members.invites.commands

import org.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import org.timemates.sdk.timers.members.invites.requests.CreateInviteRequest
import org.timemates.sdk.timers.members.invites.requests.GetInvitesRequest
import org.timemates.sdk.timers.members.invites.requests.JoinTimerByCodeRequest
import org.timemates.sdk.timers.members.invites.requests.RemoveInviteRequest

/**
 * RSocket commands related to timer members.
 */
internal fun RSocketCommandsBuilderScope.timerMemberInvites() {
    CreateInviteCommand associatedWith CreateInviteRequest
    GetInvitesCommand associatedWith GetInvitesRequest
    JoinTimerByCodeCommand associatedWith JoinTimerByCodeRequest
    RemoveInviteCommand associatedWith RemoveInviteRequest
}