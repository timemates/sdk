package org.tomadoro.sdk.types.value

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
public value class DateTime(public val long: Long)