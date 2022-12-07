package org.tomadoro.sdk.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.value.TimerId
import org.tomadoro.sdk.types.value.UserId

@Serializable
public class Timer(
    @SerialName("timer_id")
    public val timerId: TimerId,
    public val name: String,
    @SerialName("owner_id")
    public val ownerId: UserId,
    public val settings: TimerSettings
)