package io.timemates.sdk.timers.members.invites

import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.internal.flatMap
import io.timemates.sdk.common.providers.AccessHashProvider
import io.timemates.sdk.common.providers.getAsResult
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.common.types.value.PageToken
import io.timemates.sdk.timers.members.invites.requests.CreateInviteRequest
import io.timemates.sdk.timers.members.invites.requests.GetInvitesRequest
import io.timemates.sdk.timers.members.invites.requests.RemoveInviteRequest
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import io.timemates.sdk.timers.types.value.TimerId

/**
 * Provides methods for managing timer invites.
 *
 * @property engine The TimeMatesRequestsEngine used for executing requests.
 * @property tokenProvider The AccessHashProvider used for obtaining access tokens.
 */
public class TimerInvitesApi(
    private val engine: TimeMatesRequestsEngine,
    private val tokenProvider: AccessHashProvider,
) {

    /**
     * Creates an invitation for the specified timer.
     *
     * @param timerId The identifier of the timer for which the invite is created.
     * @param maxJoinersCount The maximum number of joiners allowed for the invite.
     * @return A [Result] containing the invite code if successful, or an error if unsuccessful.
     */
    public suspend fun create(timerId: TimerId, maxJoinersCount: Count): Result<InviteCode> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(CreateInviteRequest(token, timerId, maxJoinersCount)) }
            .map { it.inviteCode }
    }

    /**
     * Retrieves the invites for the specified timer.
     *
     * @param timerId The identifier of the timer for which to retrieve invites.
     * @param pageToken The token representing the next page of results, or null for the first page.
     * @return A [Result] containing the result of the get invites request if successful, or an error if unsuccessful.
     */
    public suspend fun getInvites(timerId: TimerId, pageToken: PageToken?): Result<GetInvitesRequest.Result> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(GetInvitesRequest(token, timerId, pageToken)) }
    }

    /**
     * Removes an invite for the specified timer.
     *
     * @param timerId The identifier of the timer for which to remove the invite.
     * @param inviteCode The code of the invite to remove.
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun removeInvite(timerId: TimerId, inviteCode: InviteCode): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(RemoveInviteRequest(token, timerId, inviteCode)) }
            .map { Empty }
    }
}
