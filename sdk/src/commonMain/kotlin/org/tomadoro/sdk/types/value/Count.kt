package org.tomadoro.sdk.types.value

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
public value class Count(public val int: Int) {
    init {
        require(int >= 0) { "Count should be equal or be bigger than zero" }
    }
}