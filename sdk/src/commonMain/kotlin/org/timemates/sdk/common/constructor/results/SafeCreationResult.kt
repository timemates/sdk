package org.timemates.sdk.common.constructor.results

import org.timemates.sdk.common.constructor.CreationFailure
import org.timemates.sdk.common.constructor.ValidationException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

public sealed interface SafeCreationResult<out TRaw, out TBoxed> {
    public val value: TRaw

    public class Valid<TRaw, TBoxed> internal constructor(
        public override val value: TRaw,
        public val boxed: TBoxed,
    ) : SafeCreationResult<TRaw, TBoxed>

    public class Invalid<TRaw> internal constructor(
        public override val value: TRaw,
        public val failures: List<CreationFailure>,
    ) : SafeCreationResult<TRaw, Nothing> {
        public constructor(value: TRaw, failure: CreationFailure) : this(value, listOf(failure))
    }
}

@OptIn(ExperimentalContracts::class)
public fun SafeCreationResult<*, *>.isValid(): Boolean {
    contract {
        returns(true) implies (this@isValid is SafeCreationResult.Valid<*, *>)
        returns(false) implies (this@isValid is SafeCreationResult.Invalid)
    }

    return this is SafeCreationResult.Valid
}

@OptIn(ExperimentalContracts::class)
public fun SafeCreationResult<*, *>.isInvalid(): Boolean {
    contract {
        returns(true) implies (this@isInvalid is SafeCreationResult.Invalid)
        returns(false) implies (this@isInvalid is SafeCreationResult.Valid<*, *>)
    }

    return this is SafeCreationResult.Invalid<*>
}

public fun <TRaw, TBoxed> SafeCreationResult<TRaw, TBoxed>.getUnsafe(): TBoxed {
    return if (isValid()) {
        @Suppress("UNCHECKED_CAST")
        boxed as TBoxed
    } else {
        throw ValidationException(failures)
    }
}

public fun <TRaw, TBoxed> SafeCreationResult<TRaw, TBoxed>.toResult(): Result<TBoxed> = runCatching {
    getUnsafe()
}

public val SafeCreationResult<*, *>.failuresIfPresent: List<CreationFailure>
    get() {
        return if (this is SafeCreationResult.Invalid) failures else emptyList()
    }