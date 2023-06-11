package io.timemates.sdk.common.exceptions

/**
 * Represents an exception that is thrown when a request is unauthorized or authentication fails.
 *
 * This class is a specific type of `TimeMatesException` and provides information about the unauthorized request.
 *
 * @property message The error message associated with the unauthorized exception.
 */
public data class UnauthorizedException(
    override val message: String,
    override val cause: Throwable? = null
) : TimeMatesException(message, cause) {
    public companion object {
        /**
         * The error message that denotes that client did not provide any token
         * to `AccessHashProvider`.
         *
         * @see io.timemates.sdk.common.providers.AccessHashProvider
         */
        public fun noToken(): UnauthorizedException = UnauthorizedException("No token is provided.")
    }
}