package io.timemates.api.rsocket.timers.members.invites.commands

import io.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import io.timemates.sdk.timers.members.invites.requests.CreateInviteRequest
import io.timemates.sdk.timers.members.invites.requests.GetInvitesRequest
import io.timemates.sdk.timers.members.invites.requests.JoinTimerByCodeRequest
import io.timemates.sdk.timers.members.invites.requests.RemoveInviteRequest

/**
 * RSocket commands related to timer members.
 */
internal fun RSocketCommandsBuilderScope.timerMemberInvites() {
    CreateInviteCommand associatedWith CreateInviteRequest
    GetInvitesCommand associatedWith GetInvitesRequest
    JoinTimerByCodeCommand associatedWith JoinTimerByCodeRequest
    RemoveInviteCommand associatedWith RemoveInviteRequest
}