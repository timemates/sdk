package io.timemates.api.rsocket.common.metadata

import io.ktor.utils.io.core.BytePacketBuilder
import io.ktor.utils.io.core.ByteReadPacket
import io.ktor.utils.io.core.internal.ChunkBuffer
import io.ktor.utils.io.core.isNotEmpty
import io.ktor.utils.io.core.readTextExactBytes
import io.ktor.utils.io.core.writeFully
import io.ktor.utils.io.pool.ObjectPool
import io.rsocket.kotlin.ExperimentalMetadataApi
import io.rsocket.kotlin.core.MimeType
import io.rsocket.kotlin.core.WellKnownMimeType
import io.rsocket.kotlin.metadata.Metadata
import io.rsocket.kotlin.metadata.MetadataReader

@ExperimentalMetadataApi
internal class AuthorizationMetadata(val authorization: String) : Metadata {

    override val mimeType: MimeType get() = Reader.mimeType

    override fun BytePacketBuilder.writeSelf() {
        val bytes = authorization.encodeToByteArray()
        writeByte(bytes.size.toByte())
        writeFully(bytes)
    }

    override fun close(): Unit = Unit

    companion object Reader : MetadataReader<AuthorizationMetadata> {
        override val mimeType: MimeType get() = WellKnownMimeType.MessageRSocketRouting
        override fun ByteReadPacket.read(pool: ObjectPool<ChunkBuffer>): AuthorizationMetadata {
            return AuthorizationMetadata(
                readTextExactBytes(readByte().toInt() and 0xFF)
            )
        }
    }
}
