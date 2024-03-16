package org.timemates.sdk.common.constructor.results

import org.timemates.sdk.common.constructor.CreationFailure
import kotlin.jvm.JvmInline

public sealed interface ValidationResult {
    public data object Valid : ValidationResult

    @JvmInline
    public value class Invalid(public val failure: CreationFailure) : ValidationResult
}