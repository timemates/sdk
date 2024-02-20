package org.timemates.sdk.timers.members.invites

import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.common.engine.TimeMatesRequestsEngine
import org.timemates.sdk.common.internal.flatMap
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PagesIterator
import org.timemates.sdk.common.pagination.PagesIteratorImpl
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.sdk.common.providers.AccessHashProvider
import org.timemates.sdk.common.providers.getAsResult
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.value.Count
import org.timemates.sdk.timers.members.invites.requests.CreateInviteRequest
import org.timemates.sdk.timers.members.invites.requests.GetInvitesRequest
import org.timemates.sdk.timers.members.invites.requests.RemoveInviteRequest
import org.timemates.sdk.timers.members.invites.types.Invite
import org.timemates.sdk.timers.members.invites.types.value.InviteCode
import org.timemates.sdk.timers.types.value.TimerId
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

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
    public suspend fun getInvites(timerId: TimerId, pageToken: PageToken?): Result<Page<Invite>> {
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
    }
}

public fun TimerInvitesApi.getInvitesPages(
    timerId: TimerId,
    pageToken: PageToken? = null,
    maxRetries: Count = Count.createOrThrow(5),
    initialDelayOnRetries: Duration = 1.seconds,
): PagesIterator<Invite> = PagesIteratorImpl(
    initialPageToken = pageToken,
    maxRetries = maxRetries,
    delayOnServerErrors = initialDelayOnRetries,
    provider = { _, pageToken -> getInvites(timerId, pageToken) },
)
