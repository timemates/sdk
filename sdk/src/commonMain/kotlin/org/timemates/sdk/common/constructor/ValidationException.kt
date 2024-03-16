package org.timemates.sdk.common.constructor

public data class ValidationException(
    val failures: List<CreationFailure>,
) : Exception("The following validation constraints have failed: \n ${failures.joinToString("\n") { "1. ${it.message}" }}")