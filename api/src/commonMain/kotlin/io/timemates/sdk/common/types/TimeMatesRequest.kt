package io.timemates.sdk.common.types

/**
 * Abstract class-marker representing a TimeMates request.
 *
 * The [TimeMatesRequest] class serves as a base class for all TimeMates request implementations.
 * It extends the [TimeMatesEntity] class, indicating that requests are entities in the TimeMates system.
 *
 * @param R The type of the response expected from the request.
 */
public abstract class TimeMatesRequest<R : TimeMatesEntity> internal constructor() : TimeMatesEntity()