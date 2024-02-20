package org.timemates.sdk.authorization

import org.timemates.sdk.authorization.email.EmailAuthorizationApi
import org.timemates.sdk.common.engine.TimeMatesRequestsEngine

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
