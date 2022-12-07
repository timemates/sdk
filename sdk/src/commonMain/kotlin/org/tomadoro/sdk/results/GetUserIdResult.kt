package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.value.UserId

@Serializable
public sealed interface GetUserIdResult {
    @Serializable
    @SerialName("success")
    public class Success(
        @SerialName("user_id") public val userId: UserId
    ) : GetUserIdResult
}