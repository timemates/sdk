@file:Suppress("CanBeParameter", "MemberVisibilityCanBePrivate")

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
public sealed class CreationFailure(message: String) : TimeMatesException(message) {
    /**
     * Represents a creation failure due to a size range constraint.
     */
    public class SizeRangeFailure(public val range: IntRange) : CreationFailure("Constraint failure: size must be in range of $range")

    /**
     * Represents a creation failure due to an exact size constraint.
     */
    public class SizeExactFailure(public val size: Int) : CreationFailure("Constraint failure: size must be exactly $size")

    /**
     * Represents a creation failure due to a minimum value constraint.
     */
    public class MinValueFailure(public val size: Int) : CreationFailure("Constraint failure: minimal value is $size")

    /**
     * Represents a creation failure due to a blank value constraint.
     */
    public class BlankValueFailure : CreationFailure("Constraint failure: provided value is empty")

    /**
     * Represents a creation failure due to a pattern constraint.
     */
    public class PatternFailure(public val regex: Regex) : CreationFailure("Constraint failure: input should match $regex")

    public companion object {
        /**
         * Creates a [SizeRangeFailure] object with a size constraint failure message.
         *
         * @param size The size range constraint for the creation failure.
         * @return The [SizeRangeFailure] object with the specified size constraint failure message.
         */
        public fun ofSizeRange(size: IntRange): CreationFailure {
            return SizeRangeFailure(size)
        }

        /**
         * Creates a [SizeExactFailure] with a constraint failure message based on the provided size.
         *
         * @param size The expected size that caused the constraint failure.
         * @return A [SizeExactFailure] object with the constraint failure message.
         */
        public fun ofSizeExact(size: Int): CreationFailure {
            return SizeExactFailure(size)
        }

        /**
         * Creates a [MinValueFailure] with a constraint failure message for a minimum value.
         *
         * @param size The minimal value that caused the constraint failure.
         * @return A [MinValueFailure] object with the constraint failure message.
         */
        public fun ofMin(size: Int): CreationFailure {
            return MinValueFailure(size)
        }

        /**
         * Creates a [BlankValueFailure] with a constraint failure message for a blank value.
         *
         * @return A [BlankValueFailure] object with the constraint failure message.
         */
        public fun ofBlank(): CreationFailure {
            return BlankValueFailure()
        }

        /**
         * Creates a [PatternFailure] object with a pattern constraint failure message.
         *
         * @param regex The regular expression pattern constraint for the creation failure.
         * @return The [PatternFailure] object with the specified pattern constraint failure message.
         */
        public fun ofPattern(regex: Regex): CreationFailure {
            return PatternFailure(regex)
        }
    }
}