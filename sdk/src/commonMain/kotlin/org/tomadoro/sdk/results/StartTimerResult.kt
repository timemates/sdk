package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface StartTimerResult {
    @Serializable
    @SerialName("success")
    public object Success : StartTimerResult

    @Serializable
    @SerialName("no_access")
    public object NoAccess : StartTimerResult
}