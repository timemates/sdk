package org.timemates.sdk.common.exceptions

/**
 * Represents a base exception class for TimeMates-related exceptions.
 *
 * This class is abstract and serves as the base class for all TimeMates-specific exceptions.
 *
 * @property message The error message associated with the exception.
 * @property cause The cause of the exception, if any.
 */
public abstract class TimeMatesException internal constructor(
    override val message: String,
    override val cause: Throwable? = null,
) : Exception(message, cause)