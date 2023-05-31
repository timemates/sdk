package io.timemates.sdk.authorization

import io.timemates.sdk.authorization.email.EmailAuthorizationApi
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine

/**
 * Represents an API for authorization operations.
 *
 * @param engine The engine responsible for sending authorization requests.
 */
public class AccountLoginApi(engine: TimeMatesRequestsEngine) {
    /**
     * API for email authorization methods.
     */
    public val email: EmailAuthorizationApi = EmailAuthorizationApi(engine)
}
