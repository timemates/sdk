package org.timemates.sdk.authorization.sessions.types

import org.timemates.sdk.authorization.sessions.types.value.ApplicationName
import org.timemates.sdk.authorization.sessions.types.value.ClientIpAddress
import org.timemates.sdk.authorization.sessions.types.value.ClientVersion
import org.timemates.sdk.authorization.types.value.HashValue
import org.timemates.sdk.common.types.TimeMatesEntity
import org.timemates.sdk.users.profile.types.value.UserId
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