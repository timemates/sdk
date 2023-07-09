package io.timemates.android.grcp

import android.content.Context
import io.grpc.ManagedChannel
import io.grpc.android.AndroidChannelBuilder
import io.timemates.api.grpc.factory.GrpcEngineBuilder

class AndroidGrpcEngineBuilder(private val context: Context) : GrpcEngineBuilder {
    override fun createGrpcEngine(endpoint: String): ManagedChannel {
        return AndroidChannelBuilder.forTarget(endpoint)
            .useTransportSecurity()
            .context(context)
            .build()
    }
}