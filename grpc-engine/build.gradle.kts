import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.projects
import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.google.protobuf)
    alias(libs.plugins.library.publish)
}

dependencies {
    implementation(libs.kotlinx.coroutines)
    implementation(libs.grpc.kotlin.stub)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.netty)
    implementation(libs.protobuf.kotlin)
    implementation(libs.protobuf.java)

    implementation(libs.kotlinx.datetime)
    implementation(projects.sdk)
}

kotlin {
    explicitApi()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.22.2"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.54.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

group = "io.timemates"

deployLibrary {
    ssh(tag = "maven.timemates.io") {
        host = System.getenv("TIMEMATES_SSH_HOST")
        user = System.getenv("TIMEMATES_SSH_USER")
        password = System.getenv("TIMEMATES_SSH_PASSWORD")
        deployPath = System.getenv("TIMEMATES_SSH_DEPLOY_PATH")

        group = "io.timemates"
        componentName = "kotlin"
        artifactId = "grpc-engine"
        name = "grpc-engine"

        description = "TimeMates grpc adapter for SDK"

        version = System.getenv("TIMEMATES_SDK_VERSION")
    }
}