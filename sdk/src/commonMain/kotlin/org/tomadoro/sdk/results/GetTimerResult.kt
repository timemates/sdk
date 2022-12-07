package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.Timer

@Serializable
public sealed interface GetTimerResult {
    @Serializable
    @SerialName("success")
    public class Success(public val timer: Timer) : GetTimerResult

    @Serializable
    @SerialName("not_found")
    public object NotFound : GetTimerResult
}