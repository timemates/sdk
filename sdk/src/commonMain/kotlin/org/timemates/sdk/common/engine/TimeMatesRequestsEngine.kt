package org.timemates.sdk.common.engine

import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest
import org.timemates.sdk.common.exceptions.NotFoundException
import org.timemates.sdk.common.exceptions.TooManyRequestsException
import org.timemates.sdk.common.exceptions.UnavailableException

/**
 * Represents the engine for executing TimeMates requests.
 *
 * This interface defines the contract for executing TimeMates requests and handling the associated exceptions.
 */
public interface TimeMatesRequestsEngine {
    /**
     * Executes the provided TimeMates request.
     *
     * It executes the specified `request` and handles the associated failures from server / client.
     *
     * @param request The TimeMates request to be executed.
     *
     * Returns:
     * - [NotFoundException] If the requested resource is not found.
     * - [TooManyRequestsException] If there are too many requests being made.
     * - [UnavailableException] If the method or service is unavailable.
     */
    public suspend fun <T : TimeMatesEntity> execute(request: TimeMatesRequest<T>): Result<T>
}
