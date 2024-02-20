package org.timemates.api.rsocket.common.commands

import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.common.types.TimeMatesRequest

@RSocketCommandsDsl
@JvmInline
internal value class RSocketCommandsBuilderScope(
    private val commands: MutableMap<TimeMatesRequest.Key<*>, RSocketCommand<*, *>> =
        mutableMapOf(),
) {
    fun <T : TimeMatesRequest<R>, R : TimeMatesEntity> associate(
        key: TimeMatesRequest.Key<T>,
        command: RSocketCommand<T, R>,
    ) {
        commands += key to command
    }


    infix fun <T : TimeMatesRequest<R>, R : TimeMatesEntity> RSocketCommand<T, R>.associatedWith(
        key: TimeMatesRequest.Key<T>,
    ) {
        associate(key, this)
    }

    fun build(): RSocketCommands = RSocketCommands(commands)
}

@DslMarker
internal annotation class RSocketCommandsDsl

@RSocketCommandsDsl
internal inline fun rSocketCommands(
    block: RSocketCommandsBuilderScope.() -> Unit,
): RSocketCommands {
    return RSocketCommandsBuilderScope().apply(block).build()
}