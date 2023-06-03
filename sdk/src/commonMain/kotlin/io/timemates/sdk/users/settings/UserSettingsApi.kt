package io.timemates.sdk.users.settings

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.annotations.UnimplementedApi
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.providers.AccessHashProvider
import io.timemates.sdk.users.profile.types.value.EmailAddress

public class UserSettingsApi(
    private val engine: TimeMatesRequestsEngine,
    private val tokenProvider: AccessHashProvider,
) {
    /**
     * Edits the email address associated with the current user's profile.
     * This API method is not yet implemented and will always throw a TODO exception.
     *
     * @param emailAddress The new email address to set for the user's profile.
     * @throws NotImplementedError This API method is not implemented yet.
     */
    @Suppress("RedundantSuspendModifier")
    @UnimplementedApi
    public suspend fun editEmail(emailAddress: EmailAddress): Nothing = TODO()
}