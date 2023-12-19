package io.timemates.sdk.authorization.email

import io.timemates.sdk.authorization.email.requests.ConfigureNewAccountRequest
import io.timemates.sdk.authorization.email.requests.ConfirmAuthorizationRequest
import io.timemates.sdk.authorization.email.requests.StartAuthorizationRequest
import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.authorization.sessions.types.Authorization
import io.timemates.sdk.authorization.sessions.types.value.ConfirmationCode
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.users.profile.types.value.EmailAddress
import io.timemates.sdk.users.profile.types.value.UserDescription
import io.timemates.sdk.users.profile.types.value.UserName

/**
 * This class provides an API for authorizing email addresses using the TimeMatesRequestsEngine.
 */
public class EmailAuthorizationApi(private val engine: TimeMatesRequestsEngine) {
    /**
     * Authorizes an email address and returns a Result with the verification hash.
     * @param emailAddress The email address to authorize.
     * @return A Result object with the verification hash if successful, or an error if unsuccessful.
     *
     * @see confirm
     */
    public suspend fun authorize(
        emailAddress: EmailAddress,
        metadata: Authorization.Metadata,
    ): Result<VerificationHash> {
        return engine.execute(StartAuthorizationRequest(emailAddress, metadata))
            .map { it.verificationHash }
    }

    /**
     * Confirms the authorization by email using the verification hash and confirmation code.
     * @param verificationHash The verification hash received during authorization.
     * @param confirmationCode The confirmation code to confirm the authorization.
     * @return A Result object with the response if successful, or an error if unsuccessful.
     *
     * @see authorize
     */
    public suspend fun confirm(
        verificationHash: VerificationHash,
        confirmationCode: ConfirmationCode,
    ): Result<ConfirmAuthorizationRequest.Result> {
        return engine.execute(ConfirmAuthorizationRequest(verificationHash, confirmationCode))
    }

    public suspend fun createProfile(
        verificationHash: VerificationHash,
        name: UserName,
        description: UserDescription,
    ): Result<ConfigureNewAccountRequest.Result> {
        return engine.execute(ConfigureNewAccountRequest(verificationHash, name, description))
    }
}