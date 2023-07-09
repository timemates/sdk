package io.timemates.api.grpc.factory

import io.grpc.ManagedChannel

public interface GrpcEngineBuilder {
    public fun createGrpcEngine(endpoint: String): ManagedChannel
}