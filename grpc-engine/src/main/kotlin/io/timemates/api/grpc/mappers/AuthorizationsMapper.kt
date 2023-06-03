package io.timemates.api.grpc.mappers

import io.timemates.api.authorizations.types.AuthorizationOuterClass.Authorization
import io.timemates.sdk.authorization.sessions.types.value.ClientIpAddress
import io.timemates.sdk.authorization.sessions.types.value.ClientName
import io.timemates.sdk.authorization.sessions.types.value.ClientVersion
import io.timemates.sdk.authorization.types.value.HashValue
import io.timemates.sdk.common.constructor.createOrThrow
import kotlinx.datetime.Instant
import io.timemates.sdk.authorization.sessions.types.Authorization as SdkAuthorization

internal class AuthorizationsMapper {
    fun grpcAuthorizationToSdkAuthorization(
        authorization: Authorization
    ): SdkAuthorization = with(authorization) {
        return@with SdkAuthorization(
            accessHash = SdkAuthorization.Hash(
                value = HashValue.createOrThrow(accessHash.value),
                expiresAt = Instant.fromEpochMilliseconds(accessHash.expiresAt),
            ),
            refreshHash = SdkAuthorization.Hash(
                value = HashValue.createOrThrow(refreshHash.value),
                expiresAt = Instant.fromEpochMilliseconds(refreshHash.expiresAt),
            ),
            generationTime = Instant.fromEpochMilliseconds(generationTime),
            metadata = grpcMetadataToSdkMetadata(metadata),
        )
    }

    private fun grpcMetadataToSdkMetadata(
        metadata: Authorization.Metadata,
    ): SdkAuthorization.Metadata = with(metadata) {
        return@with SdkAuthorization.Metadata(
            clientName = ClientName.createOrThrow(clientName),
            clientVersion = ClientVersion.createOrThrow(clientVersion),
            clientIpAddress = ClientIpAddress.createOrThrow(clientIpAddress),
        )
    }
}