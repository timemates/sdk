package org.timemates.sdk.common.constructor.rules

import org.timemates.sdk.common.constructor.results.ValidationResult

public interface ValidationRule<Input> {
    public fun validate(value: Input): ValidationResult

    public companion object
}

