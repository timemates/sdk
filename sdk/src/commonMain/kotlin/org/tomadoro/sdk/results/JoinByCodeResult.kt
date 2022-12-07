package org.tomadoro.sdk.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.value.TimerId

@Serializable
public sealed interface JoinByCodeResult {
    @Serializable
    @SerialName("success")
    public class Success(
        @SerialName("timer_id")
        public val timerId: TimerId
    ) : JoinByCodeResult

    @Serializable
    @SerialName("not_found")
    public object NotFound : JoinByCodeResult
}