package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.value.AccessToken

@Serializable
public sealed interface SignWithGoogleResult {
    @SerialName("success")
    @Serializable
    public class Success(
        @SerialName("access_token")
        public val accessToken: AccessToken
    ) : SignWithGoogleResult
}