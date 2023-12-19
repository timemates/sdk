package io.timemates.sdk.common.types

import io.timemates.sdk.authorization.types.value.AccessHash

/**
 * An abstract class representing an authorized request within the TimeMates system.
 * Subclasses of this class are used to perform operations that require valid
 * authorization via an access hash.
 *
 * @param R The response type expected from the request.
 */
public abstract class AuthorizedTimeMatesRequest<R : TimeMatesEntity> : TimeMatesRequest<R>() {
    /**
     * The access hash associated with this authorized request.
     * It is used for authentication and authorization purposes.
     */
    public abstract val accessHash: AccessHash
}