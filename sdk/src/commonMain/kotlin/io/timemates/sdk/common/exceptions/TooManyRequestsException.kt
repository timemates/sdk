package io.timemates.sdk.common.exceptions

/**
 * Represents an exception that is thrown when there are too many requests being made.
 *
 * This class is a specific type of `TimeMatesException` and provides information about the excessive requests.
 *
 * @property message The error message associated with the too many requests exception.
 */
public data class TooManyRequestsException(
    override val message: String,
    override val cause: Throwable?,
) : TimeMatesException(message)