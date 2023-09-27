package io.timemates.api.rsocket

import io.timemates.api.rsocket.authorizations.commands.authorizations
import io.timemates.api.rsocket.common.commands.rSocketCommands
import io.timemates.api.rsocket.files.commands.files
import io.timemates.api.rsocket.timers.commands.timers
import io.timemates.api.rsocket.users.commands.users

/**
 * Registry for RSocket commands used in the TimeMates application.
 *
 * The [rSocketCommandsRegistry] is responsible for initializing and registering RSocket commands
 * for specific TimeMates features or functionalities.
 */
internal val rSocketCommandsRegistry = rSocketCommands {
    authorizations()
    users()
    files()
    timers()
}
