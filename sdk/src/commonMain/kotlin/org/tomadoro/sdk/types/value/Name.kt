package org.tomadoro.sdk.types.value

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
public value class Name(public val string: String) {
    init {
        require(string.length <= 50) {
            "Name length should not more than 50, but got ${string.length}"
        }
    }
}