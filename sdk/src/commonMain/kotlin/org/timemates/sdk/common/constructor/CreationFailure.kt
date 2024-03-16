@file:Suppress("CanBeParameter", "MemberVisibilityCanBePrivate")

package org.timemates.sdk.common.constructor

import org.timemates.sdk.common.types.TimeMatesEntity

/**
 * Represents a failure that occurs during the creation of an object.
 *
 * This class extends the `TimeMatesException` class and provides additional functionality
 * specific to creation failures.
 *
 * @property message The error message associated with the creation failure.
 */
public sealed class CreationFailure(
    public val message: String,
) : TimeMatesEntity() {
    /**
     * Represents a creation failure due to a size range constraint.
     */
    public data class LengthRangeFailure(public val range: IntRange) : CreationFailure("Length must be in range of $range")

    /**
     * Represents a creation failure due to an exact size constraint.
     */
    public data class LengthExactFailure(public val size: Int) : CreationFailure("Length must be exactly $size")

    /**
     * Represents a creation failure due to a minimum value constraint.
     */
    public data class MinValueFailure<T>(
        public val size: T,
    ) : CreationFailure("Minimal value is $size")
        where T : Number, T : Comparable<T>

    /**
     * Represents a creation failure due to a invalid value range.
     */
    public data class ValueRangeFailure<T>(
        public val range: ClosedRange<T>,
    ) : CreationFailure("Value should be in range $range.")
        where T : Number, T : Comparable<T>

    /**
     * Represents a creation failure due to a blank value constraint.
     */
    public class BlankValueFailure : CreationFailure("Provided value is empty")

    /**
     * Represents a creation failure due to a pattern constraint.
     */
    public data class PatternFailure(public val regex: Regex) : CreationFailure("Input should match $regex")

    public data class CompoundFailure(
        public val failures: List<CreationFailure>,
    ) : CreationFailure(
        "Multiple validation was failed: \n " +
            failures.withIndex().joinToString("\n") { "${it.index + 1}. ${it.value.message}" }
    )

    public companion object {
        public fun <T> ofValueRange(
            range: ClosedRange<T>,
        ): CreationFailure where T : Number, T : Comparable<T> {
            return ValueRangeFailure(range)
        }

        /**
         * Creates a [LengthRangeFailure] object with a size constraint failure message.
         *
         * @param size The size range constraint for the creation failure.
         * @return The [LengthRangeFailure] object with the specified size constraint failure message.
         */
        public fun ofLengthRange(size: IntRange): CreationFailure {
            return LengthRangeFailure(size)
        }

        /**
         * Creates a [LengthExactFailure] with a constraint failure message based on the provided size.
         *
         * @param size The expected size that caused the constraint failure.
         * @return A [LengthExactFailure] object with the constraint failure message.
         */
        public fun ofLengthExact(size: Int): CreationFailure {
            return LengthExactFailure(size)
        }

        /**
         * Creates a [MinValueFailure] with a constraint failure message for a minimum value.
         *
         * @param size The minimal value that caused the constraint failure.
         * @return A [MinValueFailure] object with the constraint failure message.
         */
        public fun <T> ofMin(size: T): CreationFailure where T : Number, T : Comparable<T> {
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