package io.timemates.api.grpc.internal

/**
 * Applies the given transformation function to the exception contained in the `Result` and returns a new `Result`
 * with the transformed exception. If the `Result` does not contain an exception,
 * it returns the original `Result` unchanged.
 *
 * @param transform The transformation function to apply to the exception.
 * @return A new `Result` with the transformed exception, or the original `Result` if no exception is present.
 */
@JvmSynthetic
internal inline fun <T> Result<T>.mapException(transform: (Throwable) -> Throwable): Result<T> {
    val exception = exceptionOrNull() ?: return this

    return Result.failure(transform(exception))
}
