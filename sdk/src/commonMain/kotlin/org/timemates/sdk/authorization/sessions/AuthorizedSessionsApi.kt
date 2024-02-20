package org.timemates.sdk.authorization.sessions

import org.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import org.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import org.timemates.sdk.authorization.sessions.types.Authorization
import org.timemates.sdk.authorization.sessions.types.value.AuthorizationId
import org.timemates.sdk.common.annotations.UnimplementedApi
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.common.engine.TimeMatesRequestsEngine
import org.timemates.sdk.common.internal.flatMap
import org.timemates.sdk.common.pagination.Page
import org.timemates.sdk.common.pagination.PageToken
import org.timemates.sdk.common.pagination.PagesIterator
import org.timemates.sdk.common.pagination.PagesIteratorImpl
import org.timemates.sdk.common.providers.AccessHashProvider
import org.timemates.sdk.common.providers.getAsResult
import org.timemates.sdk.common.types.Empty
import org.timemates.sdk.common.types.value.Count
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

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
    public suspend fun getSessions(pageToken: PageToken?): Result<Page<Authorization>> {
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

@Suppress("NAME_SHADOWING")
public fun AuthorizedSessionsApi.getSessionsPages(
    pageToken: PageToken?,
    maxRetries: Count = Count.createOrThrow(5),
    initialDelayOnRetries: Duration = 1.seconds,
): PagesIterator<Authorization> = PagesIteratorImpl(
    initialPageToken = pageToken,
    delayOnServerErrors = initialDelayOnRetries,
    maxRetries = maxRetries,
    provider = { _, pageToken -> getSessions(pageToken) }
)