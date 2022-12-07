package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface RemoveTimerResult {
    @Serializable
    @SerialName("success")
    public object Success : RemoveTimerResult

    @Serializable
    @SerialName("not_found")
    public object NotFound : RemoveTimerResult
}