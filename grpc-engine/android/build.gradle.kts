plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.library.publish)
}

android {
    compileSdk = libs.versions.android.target.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.min.get().toInt()
    }

    namespace = "io.timemates.android.grpc"
}

dependencies {
    implementation(projects.grpcEngine)

    implementation(libs.grpc.android)
}

deployLibrary {
    ssh(tag = "maven.timemates.io") {
        host = System.getenv("TIMEMATES_SSH_HOST")
        user = System.getenv("TIMEMATES_SSH_USER")
        password = System.getenv("TIMEMATES_SSH_PASSWORD")
        deployPath = System.getenv("TIMEMATES_SSH_DEPLOY_PATH")

        group = "io.timemates"
        componentName = "release"
        artifactId = "grpc-engine-android"
        name = "grpc-engine-android"

        description = "TimeMates android grpc adapter for SDK"

        version = System.getenv("TIMEMATES_SDK_VERSION")
    }
}