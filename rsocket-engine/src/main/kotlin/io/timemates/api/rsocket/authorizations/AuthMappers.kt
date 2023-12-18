package io.timemates.api.rsocket.authorizations

import io.timemates.api.authorizations.types.Metadata
import io.timemates.sdk.authorization.sessions.types.value.ApplicationName
import io.timemates.sdk.authorization.sessions.types.value.ClientIpAddress
import io.timemates.sdk.authorization.sessions.types.value.ClientVersion
import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.authorization.types.value.HashValue
import io.timemates.sdk.common.constructor.createOrThrow
import kotlinx.datetime.Instant
import io.timemates.api.authorizations.types.Authorization as RSAuthorization
import io.timemates.sdk.authorization.sessions.types.Authorization as SdkAuth

internal fun Metadata.sdk(): SdkAuth.Metadata {
    return SdkAuth.Metadata(
        applicationName = ApplicationName.createOrThrow(clientName),
        clientVersion = ClientVersion.createOrThrow(clientVersion),
        clientIpAddress = ClientIpAddress.createOrThrow("UNDEFINED")
    )
}

internal fun SdkAuth.Metadata.rs(): Metadata {
    return Metadata(
        clientName = applicationName.string,
        clientVersion = clientVersion.double,
    )
}

internal fun RSAuthorization.sdk(): SdkAuth {
    return SdkAuth(
        accessHash = accessHash?.let { SdkAuth.Hash(HashValue.createOrThrow(it.value), Instant.fromEpochMilliseconds(it.expiresAt)) },
        refreshHash = refreshHash?.let { SdkAuth.Hash(HashValue.createOrThrow(it.value), Instant.fromEpochMilliseconds(it.expiresAt)) },
        generationTime = Instant.fromEpochMilliseconds(generationTime),
        metadata = metadata?.sdk(),
    )
}

internal fun AccessHash.toExtra(): Map<String, ByteArray> {
    return mapOf("access_hash" to string.toByteArray())
}