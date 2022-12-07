package org.tomadoro.sdk.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.tomadoro.sdk.types.value.Code
import org.tomadoro.sdk.types.value.Count

@Serializable
public class Invite(
    @SerialName("places_left")
    public val placesLeft: Count,
    public val code: Code
)