package org.timemates.api.rsocket

import org.timemates.api.rsocket.authorizations.commands.authorizations
import org.timemates.api.rsocket.common.commands.rSocketCommands
import org.timemates.api.rsocket.timers.commands.timers
import org.timemates.api.rsocket.users.commands.users

/**
 * Registry for RSocket commands used in the TimeMates application.
 *
 * The [rSocketCommandsRegistry] is responsible for initializing and registering RSocket commands
 * for specific TimeMates features or functionalities.
 */
internal val rSocketCommandsRegistry = rSocketCommands {
    authorizations()
    users()
    timers()
}
