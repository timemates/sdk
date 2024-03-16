package org.timemates.sdk.common.constructor.rules

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.results.ValidationResult

public class LengthRangeValidationRule(
    private val range: IntRange,
) : ValidationRule<String> {
    override fun validate(value: String): ValidationResult {
        return if (value.length in range)
            ValidationResult.Valid
        else ValidationResult.Invalid(CreationFailure.ofLengthRange(range))
    }
}

public fun ValidationRule.Companion.lengthRange(range: IntRange): LengthRangeValidationRule {
    return LengthRangeValidationRule(range)
}