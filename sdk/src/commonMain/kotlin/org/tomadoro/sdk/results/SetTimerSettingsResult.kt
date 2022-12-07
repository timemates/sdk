package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface SetTimerSettingsResult {
    @Serializable
    @SerialName("success")
    public object Success : SetTimerSettingsResult

    @Serializable
    @SerialName("no_access")
    public object NoAccess : SetTimerSettingsResult
}