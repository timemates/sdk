package org.timemates.sdk.timers.sessions

import org.timemates.sdk.common.engine.TimeMatesRequestsEngine
import org.timemates.sdk.common.internal.flatMap
import org.timemates.sdk.common.providers.AccessHashProvider
import org.timemates.sdk.common.providers.getAsResult
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.timers.sessions.requests.*
import org.timemates.sdk.timers.types.Timer
import org.timemates.sdk.timers.types.value.TimerId
import kotlinx.coroutines.flow.Flow

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

    /**
     * Retrieves the state of a timer for the given timer ID.
     *
     * @param timerId The ID of the timer for which to fetch the state.
     * @return A [Result] containing a [Flow] representing the timer's state.
     * The [Flow] emits the [Timer.State] updates whenever the state changes.
     */
    public suspend fun getTimerState(timerId: TimerId): Result<Flow<Timer.State>> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(GetTimerStateRequest(token, timerId)) }
            .map { result -> result.flow }
    }
}
