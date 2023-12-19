package io.timemates.sdk.authorization.sessions

import io.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import io.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.authorization.sessions.types.value.AuthorizationId
import io.timemates.sdk.common.annotations.UnimplementedApi
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.internal.flatMap
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.common.pagination.PagesIterator
import io.timemates.sdk.common.pagination.PagesIteratorImpl
import io.timemates.sdk.common.providers.AccessHashProvider
import io.timemates.sdk.common.providers.getAsResult
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.common.types.value.Count
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