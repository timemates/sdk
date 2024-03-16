package org.timemates.sdk.common.constructor.rules

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.results.ValidationResult

public class LengthValidationRule(
    private val requiredLength: Int,
) : ValidationRule<String> {
    override fun validate(value: String): ValidationResult {
        return if (value.length == requiredLength)
            ValidationResult.Valid
        else ValidationResult.Invalid(CreationFailure.ofLengthExact(requiredLength))
    }
}

public fun ValidationRule.Companion.lengthExact(requiredLength: Int): LengthValidationRule {
    return LengthValidationRule(requiredLength)
}