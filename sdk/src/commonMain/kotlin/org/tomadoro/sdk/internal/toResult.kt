package org.tomadoro.sdk.internal

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.tomadoro.sdk.exceptions.AuthorizationException
import org.tomadoro.sdk.exceptions.BadRequestException
import org.tomadoro.sdk.exceptions.ConnectionException

/**
 * Checks whether response is success or not.
 * @throws AuthorizationException if access token is invalid.
 * @throws ConnectionException if there is connection timeout.
 * @throws BadRequestException if some data is invalid (usually should not throw).
 * @return [T] if everything is okay.
 */
internal suspend inline fun <reified T> HttpResponse.toResult(): T {
    return when {
        status.isSuccess() -> body()
        status == HttpStatusCode.Unauthorized -> throw AuthorizationException()
        status == HttpStatusCode.BadRequest -> throw BadRequestException(
            bodyAsText().takeIf { it.isNotBlank() } ?: "No message"
        )

        else -> throw ConnectionException(status.description)
    }
}