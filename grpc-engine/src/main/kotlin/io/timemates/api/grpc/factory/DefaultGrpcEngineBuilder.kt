package io.timemates.api.grpc.factory

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

public class DefaultGrpcEngineBuilder : GrpcEngineBuilder {
    override fun createGrpcEngine(endpoint: String): ManagedChannel {
        return ManagedChannelBuilder.forTarget(endpoint)
            .useTransportSecurity()
            .build()
    }
}