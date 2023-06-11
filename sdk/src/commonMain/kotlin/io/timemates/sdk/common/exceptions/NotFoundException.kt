package io.timemates.sdk.common.exceptions

/**
 * Represents an exception that is thrown when a resource is not found.
 *
 * This class is a specific type of `TimeMatesException` and provides information about the resource that could not be found.
 *
 * @property message The error message associated with the not found exception.
 */
public data class NotFoundException(
    public override val message: String,
    public override val cause: Throwable? = null,
) : TimeMatesException(message, cause)