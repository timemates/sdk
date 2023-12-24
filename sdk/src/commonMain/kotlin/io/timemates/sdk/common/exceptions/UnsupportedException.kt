package io.timemates.sdk.common.exceptions

/**
 * Represents an exception that is thrown when an operation or feature is unsupported.
 *
 * This class is a specific type of [TimeMatesException] and provides information about the unsupported operation or feature.
 *
 * @property message The error message associated with the unsupported exception.
 */
public data class UnsupportedException(
    override val message: String,
    override val cause: Throwable? = null,
) : TimeMatesException(message, cause)
