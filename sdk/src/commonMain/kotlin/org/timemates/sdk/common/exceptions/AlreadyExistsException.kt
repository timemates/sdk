package org.timemates.sdk.common.exceptions

/**
 * Exception indicating that an entity already exists.
 *
 * @param message The detail message for this exception.
 * @param cause The cause of this exception.
 */
public data class AlreadyExistsException(
    override val message: String,
    override val cause: Throwable? = null
) : TimeMatesException(message, cause)
