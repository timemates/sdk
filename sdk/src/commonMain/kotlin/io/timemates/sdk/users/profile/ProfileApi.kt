package io.timemates.sdk.users.profile

import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.internal.flatMap
import io.timemates.sdk.common.providers.AccessHashProvider
import io.timemates.sdk.common.providers.getAsResult
import io.timemates.sdk.common.types.Empty
import io.timemates.sdk.users.profile.requests.EditProfileRequest
import io.timemates.sdk.users.profile.requests.GetUsersRequest
import io.timemates.sdk.users.profile.types.Avatar
import io.timemates.sdk.users.profile.types.User
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserId
import io.timemates.sdk.users.profile.types.value.UserName

/**
 * Provides functionality for accessing and modifying user profiles through the API.
 *
 * @property engine The TimeMatesRequestsEngine instance used for making API requests.
 * @property tokenProvider The provider of access hash for authentication purposes.
 */
public class ProfileApi(
    private val engine: TimeMatesRequestsEngine,
    private val tokenProvider: AccessHashProvider,
) {
    /**
     * Retrieves profiles for the specified user IDs.
     *
     * @param ids The list of user IDs to retrieve profiles for.
     * @return A Result object containing the list of User profiles if successful, or an error otherwise.
     */
    public suspend fun getProfiles(ids: List<UserId>): Result<List<User>> {
        return engine.execute(GetUsersRequest(ids)).map(GetUsersRequest.Result::users)
    }

    /**
     * Edits the current user's profile information.
     *
     * @param name The new user name (optional).
     * @param description The new user description (optional).
     * @return A Result object indicating the success of the profile edit operation.
     */
    public suspend fun editProfile(
        name: UserName? = null,
        description: UserDescription? = null,
        avatar: Avatar? = null
    ): Result<Empty> {
        return tokenProvider.getAsResult()
            .flatMap { token -> engine.execute(
                EditProfileRequest(token,  name, description, avatar)
            ) }
    }
}
