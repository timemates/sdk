package org.tomadoro.sdk.types.value

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Marks regularity of an event.
 * Should always be positive.
 */
@Serializable
@JvmInline
public value class Regularity(public val int: Int) {
    init {
        require(int > 0) {
            "Regularity should always be positive, but got $int."
        }
    }
}