package org.timemates.api.rsocket

import org.timemates.api.authorizations.AuthorizationServiceApi
import org.timemates.api.timers.TimerSessionsServiceApi
import org.timemates.api.timers.TimersServiceApi
import org.timemates.api.users.UsersServiceApi

internal class ApiContainer (
    val auth: AuthorizationServiceApi,
    val users: UsersServiceApi,
    val timers: TimersServiceApi,
    val timerSessions: TimerSessionsServiceApi,
)