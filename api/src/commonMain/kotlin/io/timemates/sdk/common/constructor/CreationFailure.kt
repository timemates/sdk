package io.timemates.sdk.common.constructor

import io.timemates.sdk.common.exceptions.TimeMatesException

/**
 * Represents a failure that occurs during the creation of an object.
 *
 * This class extends the `TimeMatesException` class and provides additional functionality
 * specific to creation failures.
 *
 * @property message The error message associated with the creation failure.
 */
public data class CreationFailure(
    public override val message: String,
) : TimeMatesException(message) {
    public companion object {
        /**
         * Creates a `CreationFailure` object with a size constraint failure message.
         *
         * @param size The size range constraint for the creation failure.
         * @return The `CreationFailure` object with the specified size constraint failure message.
         */
        public fun ofSize(size: IntRange): CreationFailure {
            return CreationFailure(
                "Constraint failure: size must be in range of $size"
            )
        }

        /**
         * Creates a [CreationFailure] with a constraint failure message based on the provided size.
         * @param size The expected size that caused the constraint failure.
         * @return A [CreationFailure] object with the constraint failure message.
         */
        public fun ofSize(size: Int): CreationFailure {
            return CreationFailure(
                "Constraint failure: size must be exactly $size"
            )
        }

        public fun ofMin(size: Int): CreationFailure {
            return CreationFailure(
                "Constraint failure: minimal value is the $size"
            )
        }

        /**
         * Creates a [CreationFailure] with a constraint failure message for a blank value.
         * @return A [CreationFailure] object with the constraint failure message.
         */
        public fun ofBlank(): CreationFailure {
            return CreationFailure("Constraint failure: provided value is empty")
        }

        /**
         * Creates a `CreationFailure` object with a pattern constraint failure message.
         *
         * @param regex The regular expression pattern constraint for the creation failure.
         * @return The `CreationFailure` object with the specified pattern constraint failure message.
         */
        public fun ofPattern(regex: Regex): CreationFailure {
            return CreationFailure(
                "Constraint failure: input should match $regex"
            )
        }
    }
}