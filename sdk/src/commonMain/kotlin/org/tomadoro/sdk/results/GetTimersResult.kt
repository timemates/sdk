package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.Timer

@Serializable
public sealed interface GetTimersResult {
    @Serializable
    @SerialName("success")
    public class Success(public val list: List<Timer>) : GetTimersResult
}