package io.timemates.sdk.files.types.value

import io.timemates.sdk.common.constructor.CreationFailure
import io.timemates.sdk.common.constructor.Factory

@JvmInline
public value class FileId(public val string: String) {
    public companion object : Factory<FileId, String>() {
        override fun create(input: String): Result<FileId> {
            return when {
                input.isBlank() -> Result.failure(CreationFailure.ofBlank())
                else -> Result.success(FileId(input))
            }
        }
    }
}