package io.timemates.api.rsocket.common.commands

import io.rsocket.kotlin.RSocket
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest

/**
 * An interface representing a command that can be executed via an RSocket connection.
 *
 * This interface is used to decouple logic by accepting an SDK entity of type [T]
 * and converting it into the engine's entity type [R] within the implementation.
 *
 * @param T The type of the SDK entity that serves as the command's input.
 * @param R The type of the engine's entity that represents the command's result.
 */
internal interface RSocketCommand<T : TimeMatesRequest<R>, R : TimeMatesEntity> {
    /**
     * Executes the command using the provided RSocket connection and input of type [T].
     *
     * @param rSocket The RSocket connection over which the command will be executed.
     * @param input The SDK entity representing the command's input.
     * @return The engine's entity representing the result of executing the command.
     * @throws RSocketException if there is an issue with the RSocket communication.
     */
    suspend fun execute(rSocket: RSocket, input: T): R
}
