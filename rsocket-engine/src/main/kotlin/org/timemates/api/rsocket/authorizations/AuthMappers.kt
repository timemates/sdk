package org.timemates.api.rsocket.authorizations

import io.ktor.utils.io.core.toByteArray
import org.timemates.api.authorizations.types.Metadata
import org.timemates.sdk.authorization.sessions.types.value.ApplicationName
import org.timemates.sdk.authorization.sessions.types.value.ClientIpAddress
import org.timemates.sdk.authorization.sessions.types.value.ClientVersion
import org.timemates.sdk.authorization.types.value.AccessHash
import org.timemates.sdk.authorization.types.value.HashValue
import org.timemates.sdk.common.constructor.createOrThrow
import org.timemates.sdk.users.profile.types.value.UserId
import kotlinx.datetime.Instant
import org.timemates.api.authorizations.types.Authorization as RSAuthorization
import org.timemates.sdk.authorization.sessions.types.Authorization as SdkAuth

internal fun Metadata.sdk(): SdkAuth.Metadata {
    return SdkAuth.Metadata(
        applicationName = ApplicationName.factory.createOrThrow(clientName),
        clientVersion = ClientVersion.factory.createOrThrow(clientVersion),
        clientIpAddress = ClientIpAddress.factory.createOrThrow("UNDEFINED")
    )
}

internal fun SdkAuth.Metadata.rs(): Metadata {
    return Metadata {
        clientName = applicationName.string
        clientVersion = this@rs.clientVersion.double
    }
}

internal fun RSAuthorization.sdk(): SdkAuth {
    return SdkAuth(
        accessHash = accessHash?.let { SdkAuth.Hash(HashValue.factory.createOrThrow(it.value), Instant.fromEpochMilliseconds(it.expiresAt)) },
        refreshHash = refreshHash?.let { SdkAuth.Hash(HashValue.factory.createOrThrow(it.value), Instant.fromEpochMilliseconds(it.expiresAt)) },
        generationTime = Instant.fromEpochMilliseconds(generationTime),
        metadata = metadata?.sdk(),
        userId = UserId.factory.createOrThrow(userId),
    )
}

internal fun AccessHash.toExtra(): Map<String, ByteArray> {
    return mapOf("access_hash" to string.toByteArray())
}