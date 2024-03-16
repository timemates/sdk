package org.timemates.sdk.common.constructor.rules

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.results.ValidationResult

public class ValueRangeValidationRule<T>(
    private val range: ClosedRange<T>,
) : ValidationRule<T> where T : Number, T : Comparable<T> {
    override fun validate(value: T): ValidationResult {
        return if (value in range)
            ValidationResult.Valid
        else ValidationResult.Invalid(CreationFailure.ofValueRange(range))
    }
}