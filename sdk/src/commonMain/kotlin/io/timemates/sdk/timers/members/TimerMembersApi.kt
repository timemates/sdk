package io.timemates.sdk.timers.members

import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.internal.flatMap
import io.timemates.sdk.common.providers.AccessHashProvider
import io.timemates.sdk.common.providers.getAsResult
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.value.PageToken
import io.timemates.sdk.timers.members.invites.TimerInvitesApi
import io.timemates.sdk.timers.members.requests.GetMembersRequest
import io.timemates.sdk.timers.members.requests.KickMemberRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.users.profile.types.value.UserId

/**
 * Provides methods for managing timer members.
 *
 * @property engine The TimeMatesRequestsEngine used for executing requests.
 * @property tokenProvider The AccessHashProvider used for obtaining access tokens.
 * @property invites The TimerInvitesApi for managing timer invites.
 */
public class TimerMembersApi(
    private val engine: TimeMatesRequestsEngine,
    private val tokenProvider: AccessHashProvider,
) {
    public val invites: TimerInvitesApi = TimerInvitesApi(engine, tokenProvider)

    /**
     * Retrieves the members of the specified timer.
     *
     * @param timerId The identifier of the timer for which to retrieve members.
     * @param pageToken The token representing the next page of results, or null for the first page.
     * @return A [Result] containing the result of the get members request if successful, or an error if unsuccessful.
     */
    public suspend fun getMembers(timerId: TimerId, pageToken: PageToken?): Result<GetMembersRequest.Response> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(GetMembersRequest(token, timerId, pageToken)) }
    }

    /**
     * Kicks a member from the specified timer.
     *
     * @param timerId The identifier of the timer from which to kick the member.
     * @param userId The identifier of the user to kick.
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun kickMember(timerId: TimerId, userId: UserId): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(KickMemberRequest(token, timerId, userId)) }
    }
}
