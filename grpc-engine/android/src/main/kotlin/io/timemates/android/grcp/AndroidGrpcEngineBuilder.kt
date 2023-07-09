package io.timemates.android.grcp

import io.grpc.ManagedChannel
import io.grpc.android.AndroidChannelBuilder
import io.timemates.api.grpc.factory.GrpcEngineBuilder

class AndroidGrpcEngineBuilder : GrpcEngineBuilder {
    override fun createGrpcEngine(endpoint: String): ManagedChannel {
        return AndroidChannelBuilder.forTarget(endpoint)
            .useTransportSecurity()
            .build()
    }
}