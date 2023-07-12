package io.timemates.sdk.timers.sessions

import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.internal.flatMap
import io.timemates.sdk.common.providers.AccessHashProvider
import io.timemates.sdk.common.providers.getAsResult
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.timers.sessions.requests.*
import io.timemates.sdk.timers.types.Timer
import io.timemates.sdk.timers.types.value.TimerId

/**
 * Provides methods for interacting with timer sessions.
 *
 * @property engine The TimeMatesRequestsEngine used for executing requests.
 * @property tokenProvider The AccessHashProvider used for obtaining access tokens.
 */
public class TimersSessionsApi(
    private val engine: TimeMatesRequestsEngine,
    private val tokenProvider: AccessHashProvider,
) {

    /**
     * Starts a timer session for the specified timer of given [timerId].
     *
     * @param timerId The identifier of the timer to start.
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun startTimer(timerId: TimerId): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(StartTimerRequest(token, timerId)) }
    }

    /**
     * Stops running timer session for the specified timer of given [timerId].
     *
     * @param timerId The identifier of the timer to stop.
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun stopTimer(timerId: TimerId): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(StopTimerRequest(token, timerId)) }
    }

    /**
     * Joins the timer session of the given [timerId].
     *
     * @param timerId The identifier of the timer session to join.
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun joinTimerSession(timerId: TimerId): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(JoinTimerSessionRequest(token, timerId)) }
    }

    /**
     * Leaves the currently joined timer session.
     *
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun leaveTimerSession(): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(LeaveTimerSessionRequest(token)) }
    }

    /**
     * Pings the current timer session to keep it active. Should be called every
     * 5-10 minutes by the client.
     *
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun pingSession(): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(PingSessionRequest(token)) }
    }

    /**
     * Confirms the start of a timer round in the current session.
     *
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun confirmTimerRound(): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(ConfirmTimerRoundRequest(token)) }
    }

    /**
     * Returns current user session.
     *
     * @return A [Result] indicating the success or failure of the operation.
     */
    public suspend fun getUserCurrentSession(): Result<Timer> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(GetUserCurrentSessionRequest(token)) }
    }
}
