package io.timemates.sdk.authorization.sessions

import io.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import io.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.authorization.sessions.types.value.AuthorizationId
import io.timemates.sdk.common.annotations.UnimplementedApi
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.internal.flatMap
import io.timemates.sdk.common.providers.AccessHashProvider
import io.timemates.sdk.common.providers.getAsResult
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.value.PageToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

public class AuthorizedSessionsApi(
    private val engine: TimeMatesRequestsEngine,
    private val tokenProvider: AccessHashProvider,
) {
    /**
     * Retrieves a paginated list of authorization sessions.
     *
     * @param pageToken The token representing the page to retrieve. Pass `null` to start from the beginning.
     * @return The result of the API call, containing the list of authorizations and the next page token.
     */
    public suspend fun getSessions(pageToken: PageToken?): Result<GetAuthorizationSessionsRequest.Result> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(GetAuthorizationSessionsRequest(pageToken, token)) }
    }

    /**
     * Terminates the current authorization session.
     *
     * @return The result of the API call, indicating the success or failure of the termination request.
     */
    public suspend fun terminateMe(): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(TerminateCurrentAuthorizationSessionRequest(token)) }
    }

    // unimplemented on server for now
    @Suppress("RedundantSuspendModifier")
    @UnimplementedApi
    public suspend fun terminate(authorizationId: AuthorizationId): Nothing {
        TODO()
    }
}

/**
 * Retrieves a flow of authorization sessions across multiple pages.
 *
 * @param pageToken The token representing the page to retrieve. Pass `null` to start from the beginning.
 * @return A flow emitting lists of authorizations retrieved from each page.
 */
public fun AuthorizedSessionsApi.getSessionPages(
    pageToken: PageToken?,
    maxRetryCount: Int,
): Flow<List<Authorization>> = flow {
    var retryCount = 0
    var currentToken = pageToken

    while (true) {
        val response = getSessions(currentToken)

        if (response.isSuccess) {
            val result = response.getOrThrow()
            emit(result.authorizations)

            if (currentToken == result.nextPageToken)
                break

            currentToken = result.nextPageToken
            retryCount = 0
        } else {
            retryCount++
            if (retryCount > maxRetryCount) {
                break
            }

            delay(1000) // Add delay before retrying
        }
    }
}