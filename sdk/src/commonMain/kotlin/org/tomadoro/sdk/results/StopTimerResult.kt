package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface StopTimerResult {
    @Serializable
    @SerialName("success")
    public object Success : StopTimerResult

    @Serializable
    @SerialName("no_access")
    public object NoAccess : StopTimerResult
}