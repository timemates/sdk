package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface RemoveInviteResult {
    @Serializable
    @SerialName("success")
    public object Success : RemoveInviteResult

    @Serializable
    @SerialName("no_access")
    public object NoAccess : RemoveInviteResult

    @Serializable
    @SerialName("not_found")
    public object NotFound : RemoveInviteResult
}