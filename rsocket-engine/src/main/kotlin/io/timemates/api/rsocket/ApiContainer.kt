package io.timemates.api.rsocket

import io.timemates.api.authorizations.AuthorizationServiceApi
import io.timemates.api.timers.TimerSessionsServiceApi
import io.timemates.api.timers.TimersServiceApi
import io.timemates.api.users.UsersServiceApi

internal class ApiContainer (
    val auth: AuthorizationServiceApi,
    val users: UsersServiceApi,
    val timers: TimersServiceApi,
    val timerSessions: TimerSessionsServiceApi,
)