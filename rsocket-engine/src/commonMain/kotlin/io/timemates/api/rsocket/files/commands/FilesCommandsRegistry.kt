package io.timemates.api.rsocket.files.commands

import io.timemates.api.rsocket.common.commands.RSocketCommandsBuilderScope
import io.timemates.sdk.files.requests.GetFileBytesRequest

/**
 * RSocket commands related to timers.
 */
internal fun RSocketCommandsBuilderScope.files() {
    GetFileCommand associatedWith GetFileBytesRequest
}