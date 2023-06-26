package io.timemates.sdk.common.types

/**
 * Marker class for TimeMates entities.
 *
 * The [TimeMatesEntity] interface serves as a marker interface to identify entities within the TimeMates system.
 * It does not declare any methods or properties and is used solely to categorize classes as TimeMates entities.
 * Classes implementing this interface can be considered part of the TimeMates SDK.
 *
 * It is not implemented by value-objects, such as [io.timemates.sdk.users.profile.types.value.EmailAddress],
 * for example, because they considered only as a part of request / response entities.
 */
public abstract class TimeMatesEntity internal constructor()