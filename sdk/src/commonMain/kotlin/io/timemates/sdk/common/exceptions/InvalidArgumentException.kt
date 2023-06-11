package io.timemates.sdk.common.exceptions

/**
 * Represents failure from the server when given argument
 * is invalid.
 *
 * @param message The detail message for this exception.
 * @param cause The cause of this exception.
 */
public data class InvalidArgumentException(
    override val message: String,
    override val cause: Throwable?
) : TimeMatesException(message, cause)