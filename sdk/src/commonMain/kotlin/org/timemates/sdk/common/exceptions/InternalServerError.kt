package org.timemates.sdk.common.exceptions

/**
 * Represents internal failure on server.
 */
public class InternalServerError(
    message: String,
    cause: Throwable?,
) : TimeMatesException(message, cause)