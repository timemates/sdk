package io.timemates.sdk.common.exceptions

/**
 * Exception indicating that permission is denied.
 *
 * @param message The detail message for this exception.
 * @param cause The cause of this exception.
 */
public data class PermissionDeniedException(
    override val message: String,
    override val cause: Throwable? = null
) : TimeMatesException(message, cause)
