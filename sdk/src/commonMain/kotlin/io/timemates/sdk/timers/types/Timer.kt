package io.timemates.sdk.timers.types

import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.timers.types.value.TimerDescription
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.timers.types.value.TimerName
import io.timemates.sdk.users.profile.types.value.UserId
import kotlinx.datetime.Instant

public data class Timer (
    val timerId: TimerId,
    val name: TimerName,
    val description: TimerDescription,
    val ownerId: UserId,
    val membersCount: Count,
    val settings: TimerSettings,
) : TimeMatesEntity() {
    /**
     * Sealed class representing different states of a TimeMates entity.
     *
     * @property endsAt The time when the state will lose its actuality, if applicable.
     * @property publishTime The time when the state was published. It's especially useful if the state
     * was partially updated (for example, settings were changed, and the running state
     * was adjusted to these settings).
     */
    public sealed class State : TimeMatesEntity() {
        public abstract val endsAt: Instant?
        public abstract val publishTime: Instant

        /**
         * Represents a paused state of the TimeMates entity.
         * Paused states do not have an exact time to be expired and are usually paused by force
         * for an indefinite amount of time. They can be resumed only on purpose. The server may
         * decide to expire paused states after some time, but the client shouldn't focus on that
         * and should handle the state accordingly.
         *
         * @property publishTime The time when the paused state was published.
         */
        public data class Paused(
            override val publishTime: Instant,
        ) : State() {
            override val endsAt: Instant? = null
        }

        public data class ConfirmationWaiting(
            override val endsAt: Instant,
            override val publishTime: Instant,
        ) : State()

        /**
         * Represents an inactive state of the TimeMates entity.
         *
         * @property publishTime The time when the inactive state was published.
         */
        public data class Inactive(
            override val publishTime: Instant,
        ) : State() {
            override val endsAt: Instant? = null
        }

        /**
         * Represents a running state of the TimeMates entity.
         *
         * @property endsAt The time when the running state will lose its actuality.
         * @property publishTime The time when the running state was published.
         */
        public data class Running(
            override val endsAt: Instant,
            override val publishTime: Instant,
        ) : State()

        /**
         * Represents a rest state of the TimeMates entity.
         *
         * @property endsAt The time when the rest state will lose its actuality.
         * @property publishTime The time when the rest state was published.
         */
        public data class Rest(
            override val endsAt: Instant,
            override val publishTime: Instant,
        ) : State()
    }
}