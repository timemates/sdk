package io.timemates.sdk.authorization.sessions.types

import io.timemates.sdk.authorization.sessions.types.value.ApplicationName
import io.timemates.sdk.authorization.sessions.types.value.ClientIpAddress
import io.timemates.sdk.authorization.sessions.types.value.ClientVersion
import io.timemates.sdk.authorization.types.value.HashValue
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.users.profile.types.value.UserId
import kotlinx.datetime.Instant

public data class Authorization(
    val accessHash: Hash?,
    val refreshHash: Hash?,
    val generationTime: Instant,
    val metadata: Metadata?,
    val userId: UserId,
) : TimeMatesEntity() {
    public data class Hash(
        val value: HashValue,
        val expiresAt: Instant,
    ) : TimeMatesEntity()

    public data class Metadata(
        val applicationName: ApplicationName,
        val clientVersion: ClientVersion,
        val clientIpAddress: ClientIpAddress,
    ) : TimeMatesEntity()
}