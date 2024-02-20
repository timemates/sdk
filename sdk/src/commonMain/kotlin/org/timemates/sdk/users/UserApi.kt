package org.timemates.sdk.users

import org.timemates.sdk.common.engine.TimeMatesRequestsEngine
import org.timemates.sdk.common.providers.AccessHashProvider
import org.timemates.sdk.users.profile.ProfileApi
import org.timemates.sdk.users.settings.UserSettingsApi

/**
 * Provides access to user-related functionality through the API.
 *
 * @param engine The TimeMatesRequestsEngine instance used for making API requests.
 * @param tokenProvider The provider of access hash for authentication purposes.
 */
public class UserApi(engine: TimeMatesRequestsEngine, tokenProvider: AccessHashProvider) {
    /**
     * An instance of the ProfileApi class for accessing user profiles.
     */
    public val profile: ProfileApi = ProfileApi(engine, tokenProvider)

    /**
     * An instance of the UserSettingsApi class for accessing user settings.
     */
    public val settings: UserSettingsApi = UserSettingsApi(engine, tokenProvider)
}
