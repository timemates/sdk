package org.timemates.sdk.common.constructor.rules

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.results.ValidationResult

public class PatternValidationRule(
    private val regex: Regex,
) : ValidationRule<String> {
    override fun validate(value: String): ValidationResult {
        return if (value.matches(regex))
            ValidationResult.Valid
        else ValidationResult.Invalid(CreationFailure.ofPattern(regex))
    }
}

public fun ValidationRule.Companion.matchesPattern(regex: Regex): PatternValidationRule {
    return PatternValidationRule(regex)
}