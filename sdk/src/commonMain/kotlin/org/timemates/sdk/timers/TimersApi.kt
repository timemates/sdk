package org.timemates.sdk.timers

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
import org.timemates.sdk.timers.members.TimerMembersApi
import org.timemates.sdk.timers.requests.*
import org.timemates.sdk.timers.sessions.TimersSessionsApi
import org.timemates.sdk.timers.types.Timer
import org.timemates.sdk.timers.types.TimerSettings
import org.timemates.sdk.timers.types.value.TimerDescription
import org.timemates.sdk.timers.types.value.TimerId
import org.timemates.sdk.timers.types.value.TimerName
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Provides methods for managing timers.
 *
 * @property engine The TimeMatesRequestsEngine used for executing requests.
 * @property tokenProvider The AccessHashProvider used for obtaining access tokens.
 * @property members The TimerMembersApi for managing timer members.
 */
public class TimersApi(
    private val engine: TimeMatesRequestsEngine,
    private val tokenProvider: AccessHashProvider,
) {
    public val sessions: TimersSessionsApi = TimersSessionsApi(engine, tokenProvider)
    public val members: TimerMembersApi = TimerMembersApi(engine, tokenProvider)

    /**
     * Creates a new timer with the specified details.
     *
     * @param name The name of the timer.
     * @param description The description of the timer.
     * @param settings The settings of the timer.
     * @return A [Result] containing the ID of the created timer if successful, or an error if unsuccessful.
     */
    public suspend fun createTimer(
        name: TimerName,
        description: TimerDescription,
        settings: TimerSettings,
    ): Result<TimerId> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(CreateTimerRequest(token, name, description, settings)) }
            .map { it.timerId }
    }

    /**
     * Edits the specified timer with the provided details.
     *
     * @param timerId The ID of the timer to edit.
     * @param name The new name of the timer, or null to keep the existing name.
     * @param description The new description of the timer, or null to keep the existing description.
     * @param settings The new settings of the timer, or null to keep the existing settings.
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun editTimer(
        timerId: TimerId,
        name: TimerName? = null,
        description: TimerDescription? = null,
        settings: TimerSettings.Patch? = null,
    ): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token ->
                engine.execute(EditTimerRequest(token, timerId, name, description, settings))
            }
    }

    /**
     * Retrieves the specified timer.
     *
     * @param timerId The ID of the timer to retrieve.
     * @return A [Result] containing the retrieved timer if successful, or an error if unsuccessful.
     */
    public suspend fun getTimer(timerId: TimerId): Result<Timer> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(GetTimerRequest(token, timerId)) }
            .map { it.timer }
    }

    /**
     * Retrieves the timers belonging to the current user.
     *
     * @param pageToken The token representing the next page of results, or null for the first page.
     * @return A [Result] containing the result of the get user timers request if successful, or an error if unsuccessful.
     */
    public suspend fun getUserTimers(pageToken: PageToken? = null): Result<Page<Timer>> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(GetUserTimersRequest(token, pageToken)) }
    }

    /**
     * Removes the specified timer.
     *
     * @param timerId The ID of the timer to remove.
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun removeTimer(timerId: TimerId): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(RemoveTimerRequest(token, timerId)) }
    }
}

public fun TimersApi.getUserTimersPages(
    pageToken: PageToken? = null,
    maxRetries: Count = Count.createOrThrow(5),
    initialDelayOnRetries: Duration = 1.seconds,
): PagesIterator<Timer> = PagesIteratorImpl(
    initialPageToken = pageToken,
    provider = { _, pageToken -> getUserTimers(pageToken) },
    maxRetries = maxRetries,
    delayOnServerErrors = initialDelayOnRetries,
)