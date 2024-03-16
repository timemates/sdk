package org.timemates.sdk.common.constructor.rules

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.results.ValidationResult

public object ValueIsNotBlankRule : ValidationRule<String> {
    override fun validate(value: String): ValidationResult {
        return if (value.isNotBlank())
            ValidationResult.Valid
        else ValidationResult.Invalid(CreationFailure.ofBlank())
    }
}

public fun ValidationRule.Companion.notBlank(): ValueIsNotBlankRule {
    return ValueIsNotBlankRule
}