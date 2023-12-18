package io.timemates.api.rsocket.common.commands

import io.rsocket.kotlin.RSocket
import io.timemates.api.rsocket.ApiContainer
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest

/**
 * An inline class that encapsulates a set of RSocket commands for executing TimeMates requests.
 *
 * The [RSocketCommands] class allows you to execute TimeMates requests using the provided RSocket
 * instance. It maintains a map of commands associated with request keys, which are used to look up
 * the appropriate command for a given request.
 *
 * @param commands A map of request keys to RSocket command implementations.
 */
@JvmInline
internal value class RSocketCommands(
    private val commands: Map<TimeMatesRequest.Key<*>, RSocketCommand<*, *>>,
) {
    /**
     * Executes a TimeMates request using the specified RSocket instance.
     *
     * This function looks up the appropriate RSocket command based on the request's [requestKey]
     * and executes it with the provided RSocket and request object.
     *
     * @param T The specific type of the TimeMates request.
     * @param R The type of the response expected from the request.
     * @param rSocket The RSocket instance to use for executing the request.
     * @param request The TimeMates request to execute.
     */
    suspend inline fun <T : TimeMatesRequest<R>, R : TimeMatesEntity> execute(
        rSocket: ApiContainer,
        request: T,
    ): R? {
        @Suppress("UNCHECKED_CAST")
        return (commands[request.requestKey] as? RSocketCommand<T, R>)?.execute(rSocket, request)
    }
}
