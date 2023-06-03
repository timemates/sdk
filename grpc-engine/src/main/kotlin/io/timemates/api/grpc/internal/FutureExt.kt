package io.timemates.api.grpc.internal

import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal suspend fun <T> ListenableFuture<T>.executeAsResult(): Result<T> = runCatching {
    suspendCancellableCoroutine { continuation ->
        val listener = object : FutureCallback<T> {
            override fun onSuccess(result: T?) {
                result?.let {
                    continuation.resume(it)
                } ?: continuation.resumeWithException(NullPointerException("ListenableFuture returned null"))
            }

            override fun onFailure(t: Throwable) {
                continuation.resumeWithException(t)
            }
        }

        continuation.invokeOnCancellation {
            this@executeAsResult.cancel(true)
        }

        addListener(Runnable { listener.onSuccess(get()) }, MoreExecutors.directExecutor())
    }
}