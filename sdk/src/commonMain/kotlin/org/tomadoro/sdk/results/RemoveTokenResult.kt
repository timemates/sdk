package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface RemoveTokenResult {
    @Serializable
    @SerialName("success")
    public object Success : RemoveTokenResult
}