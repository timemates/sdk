package org.timemates.sdk.common.exceptions

/**
 * Represents an exception that is thrown when a method or service is unavailable.
 *
 * This class is a specific type of `TimeMatesException` and provides information about the unavailability.
 *
 * @property cause The cause of the unavailability, represented as a throwable.
 */
public data class UnavailableException(
    override val message: String,
    override val cause: Throwable
) : TimeMatesException("Method or service is unavailable: $message", cause)