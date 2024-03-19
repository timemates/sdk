package org.timemates.sdk.timers.members

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
import org.timemates.sdk.timers.members.invites.TimerInvitesApi
import org.timemates.sdk.timers.members.requests.GetMembersRequest
import org.timemates.sdk.timers.members.requests.KickMemberRequest
import org.timemates.sdk.timers.types.value.TimerId
import org.timemates.sdk.users.profile.types.User
import org.timemates.sdk.users.profile.types.value.UserId
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

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
    public suspend fun getMembers(
        timerId: TimerId,
        pageToken: PageToken? = null,
    ): Result<Page<User>> {
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

public fun TimerMembersApi.getMembersPages(
    timerId: TimerId,
    pageToken: PageToken? = null,
    maxRetries: Count = Count.factory.createOrThrow(5),
    initialDelayOnRetries: Duration = 1.seconds,
): PagesIterator<User> = PagesIteratorImpl(
    initialPageToken = pageToken,
    maxRetries = maxRetries,
    delayOnServerErrors = initialDelayOnRetries,
    provider = { _, pageToken -> getMembers(timerId, pageToken) },
)
