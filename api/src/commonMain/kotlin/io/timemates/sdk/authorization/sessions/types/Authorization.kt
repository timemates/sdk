package io.timemates.sdk.authorization.sessions.types

import io.timemates.sdk.authorization.sessions.types.value.ClientIpAddress
import io.timemates.sdk.authorization.sessions.types.value.ClientName
import io.timemates.sdk.authorization.sessions.types.value.ClientVersion
import io.timemates.sdk.authorization.types.value.HashValue
import io.timemates.sdk.common.types.TimeMatesEntity
import kotlinx.datetime.Instant

public data class Authorization(
    val accessHash: Hash?,
    val refreshHash: Hash?,
    val generationTime: Instant,
    val metadata: Metadata?
) : TimeMatesEntity() {
    public data class Hash(
        val value: HashValue,
        val expiresAt: Instant,
    ) : TimeMatesEntity()

    public data class Metadata(
        val clientName: ClientName,
        val clientVersion: ClientVersion,
        val clientIpAddress: ClientIpAddress,
    ) : TimeMatesEntity()
}