package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.Invite

@Serializable
public sealed interface GetInvitesResult {
    @Serializable
    @SerialName("success")
    public class Success(public val list: List<Invite>) : GetInvitesResult

    @Serializable
    @SerialName("no_access")
    public object NoAccess : GetInvitesResult
}