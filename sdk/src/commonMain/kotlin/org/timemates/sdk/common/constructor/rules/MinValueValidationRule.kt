package org.timemates.sdk.common.constructor.rules

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.results.ValidationResult

public class MinValueValidationRule<T>(
    private val minValue: T,
) : ValidationRule<T> where T : Number, T : Comparable<T> {
    override fun validate(value: T): ValidationResult {
        return if (value >= minValue)
            ValidationResult.Valid
        else ValidationResult.Invalid(CreationFailure.ofMin(minValue))
    }
}

public fun <T> ValidationRule.Companion.minValue(
    min: T,
): MinValueValidationRule<T> where T : Number, T : Comparable<T> {
    return MinValueValidationRule(min)
}