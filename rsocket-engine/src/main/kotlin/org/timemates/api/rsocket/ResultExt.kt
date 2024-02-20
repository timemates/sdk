package org.timemates.api.rsocket

internal fun <T> Result<T>.mapException(block: (Throwable) -> Throwable): Result<T> {
    return if(isFailure)
        Result.failure(block(exceptionOrNull()!!))
    else Result.success(getOrThrow())
}