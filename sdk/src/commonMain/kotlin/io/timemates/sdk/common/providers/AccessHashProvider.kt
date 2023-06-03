package io.timemates.sdk.common.providers

import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.exceptions.UnauthorizedException

/**
 * Interface for providing access token hashes.
 */
public interface AccessHashProvider {
    /**
     * Retrieves the access hash or null if not available.
     * @return The [AccessHash] or null.
     */
    public suspend fun getOrNull(): AccessHash?
}

public suspend fun AccessHashProvider.getAsResult(): Result<AccessHash> {
    return when (val accessHash = getOrNull()) {
        null -> Result.failure(UnauthorizedException.noToken())
        else -> Result.success(accessHash)
    }
}