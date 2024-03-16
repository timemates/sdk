package org.timemates.sdk.common.constructor

import org.timemates.sdk.common.constructor.rules.ValidationRule
import org.timemates.sdk.common.constructor.results.SafeCreationResult
import org.timemates.sdk.common.constructor.results.ValidationResult
import org.timemates.sdk.common.constructor.results.getUnsafe

/**
 * Represents a generic constructor class for creating objects of type [TBoxed] from TRaw of type [TRaw].
 *
 * This class is abstract and provides a template for creating objects. Even if there's no need
 * in validating, we should follow our code-style and provide only [Factory] API.
 *
 * Primary and secondary constructors should be private and use only [Factory] as public API.
 *
 * @param TBoxed The type of object to be created.
 * @param TRaw The type of TRaw used to create the object.
 */
public interface Factory<TBoxed, TRaw> {
    public val rules: List<ValidationRule<TRaw>>


    /**
     * Creates [TBoxed] from [TRaw] in the safe way by validating
     * [rules] that are defined in the factory.
     */
    public fun createSafe(value: TRaw): SafeCreationResult<TRaw, TBoxed>
}

public fun <TBoxed, TRaw> factory(
    rules: List<ValidationRule<TRaw>>,
    constructor: (TRaw) -> TBoxed,
): Factory<TBoxed, TRaw> {
    return object : Factory<TBoxed, TRaw> {
        override val rules: List<ValidationRule<TRaw>> by ::rules
        override fun createSafe(value: TRaw): SafeCreationResult<TRaw, TBoxed> {
            val failures = rules.mapNotNull { rule ->
                (rule.validate(value) as? ValidationResult.Invalid)?.failure
            }

            return if (failures.any())
                SafeCreationResult.Invalid(value, failures)
            else SafeCreationResult.Valid(value, constructor(value))
        }
    }
}

public fun <TBoxed, TRaw> factory(
    constructor: (TRaw) -> TBoxed,
): Factory<TBoxed, TRaw> {
    return object : Factory<TBoxed, TRaw> {
        override val rules: List<ValidationRule<TRaw>> get() = emptyList()
        override fun createSafe(value: TRaw): SafeCreationResult<TRaw, TBoxed> {
            return SafeCreationResult.Valid(value, constructor(value))
        }
    }
}

/**
 * Creates an instance of the specified [TBoxed] type using the provided [TRaw].
 *
 * This function attempts to instantiate an entity of the given type [TBoxed] based on the provided [TRaw].
 * It returns the instantiated entity if the operation is successful, or throws an exception if an error occurs
 * during the instantiation process.
 *
 * @param TRaw The TRaw required for the entity creation.
 * @return The instantiated entity of type [TBoxed].
 * @throws Throwable if an error occurs during the entity creation process.
 */
public fun <TBoxed, TRaw> Factory<TBoxed, TRaw>.createOrThrow(value: TRaw): TBoxed {
    return createSafe(value).getUnsafe()
}

/**
 * Creates an instance of the specified [TBoxed] type using the provided [TRaw].
 *
 * This function attempts to instantiate an entity of the given type [TBoxed] based on the provided [TRaw].
 * It returns the instantiated entity if the operation is successful, or returns `null` if an error occurs
 * during the instantiation process.
 *
 * @param TRaw The TRaw required for the entity creation.
 * @return The instantiated entity of type [TBoxed], or `null` if an error occurs during the entity creation process.
 */
public fun <TBoxed, TRaw> Factory<TBoxed, TRaw>.createOrNull(value: TRaw): TBoxed? {
    @Suppress("UNCHECKED_CAST")
    return (createSafe(value) as? SafeCreationResult.Valid<*, *>)?.boxed as TBoxed
}