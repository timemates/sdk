package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.value.Code

@Serializable
public sealed interface CreateInviteResult {
    @Serializable
    @SerialName("success")
    public class Success(public val code: Code) : CreateInviteResult

    @Serializable
    @SerialName("no_access")
    public object NoAccess : CreateInviteResult
}