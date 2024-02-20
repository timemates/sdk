package org.timemates.sdk.common.constructor

/**
 * Represents a generic constructor class for creating objects of type [Output] from input of type [Input].
 *
 * This class is abstract and provides a template for creating objects. Even if there's no need
 * in validating, we should follow our code-style and provide only [Factory] API.
 *
 * Primary and secondary constructors should be private and use only [Factory] as public API.
 *
 * @param Output The type of object to be created.
 * @param Input The type of input used to create the object.
 */
public abstract class Factory<Output, Input> {
    /**
     * Instantiates the entity of given type [Output].
     *
     * **Shouldn't throw anything, but instantiate object of type [Output]**
     */
    public abstract fun create(input: Input): Result<Output>
}


/**
 * Creates an instance of the specified [Output] type using the provided [input].
 *
 * This function attempts to instantiate an entity of the given type [Output] based on the provided [input].
 * It returns the instantiated entity if the operation is successful, or throws an exception if an error occurs
 * during the instantiation process.
 *
 * @param input The input required for the entity creation.
 * @return The instantiated entity of type [Output].
 * @throws Throwable if an error occurs during the entity creation process.
 */
public fun <Output, Input> Factory<Output, Input>.createOrThrow(input: Input): Output {
    val result = create(input)

    return if(result.isSuccess) {
        result.getOrThrow()
    } else {
        throw result.exceptionOrNull() ?: error("Failed to create an object.")
    }
}

/**
 * Creates an instance of the specified [Output] type using the provided [input].
 *
 * This function attempts to instantiate an entity of the given type [Output] based on the provided [input].
 * It returns the instantiated entity if the operation is successful, or returns `null` if an error occurs
 * during the instantiation process.
 *
 * @param input The input required for the entity creation.
 * @return The instantiated entity of type [Output], or `null` if an error occurs during the entity creation process.
 */
public fun <Output, Input> Factory<Output, Input>.createOrNull(input: Input): Output? {
    return create(input).getOrNull()
}
