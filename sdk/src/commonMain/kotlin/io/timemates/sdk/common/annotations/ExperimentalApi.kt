package io.timemates.sdk.common.annotations

/**
 * Annotation used to indicate that a given SDK API is experimental.
 */
@RequiresOptIn(message = "Given SDK API is experimental", level = RequiresOptIn.Level.ERROR)
public annotation class ExperimentalApi(val status: ApiStatus)

public enum class ApiStatus {
    PROTOTYPE,
    IN_PROGRESS,
    NEEDS_REVISION,
    STABLE_CANDIDATE,
}