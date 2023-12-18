plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.library.publish)
    alias(libs.plugins.timemates.rsproto)
}

kotlin {
    jvm()
    jvmToolchain(17)

    sourceSets {
        val commonMain by getting {
            kotlin.srcDirs("src/main/kotlin", "build/generated/rsproto/kotlin")
        }
    }

    explicitApi()
}

dependencies {
    commonMainImplementation(projects.sdk)

    commonMainImplementation(libs.timemates.rsproto.common)
    commonMainImplementation(libs.timemates.rsproto.client)

    commonMainImplementation(libs.kotlinx.serialization)
    commonMainImplementation(libs.kotlinx.datetime)
    commonMainImplementation(libs.rsocket.client)
}

deployLibrary {
    ssh(tag = "rsocket-engine to maven.timemates.io") {
        host = System.getenv("TIMEMATES_SSH_HOST")
        user = System.getenv("TIMEMATES_SSH_USER")
        password = System.getenv("TIMEMATES_SSH_PASSWORD")
        deployPath = System.getenv("TIMEMATES_SSH_DEPLOY_PATH")

        group = "io.timemates"
        componentName = "kotlin"
        artifactId = "rsocket-engine"
        name = "rsocket-engine"

        description = "TimeMates rsocket adapter for SDK"

        version = System.getenv("TIMEMATES_SDK_VERSION")
    }
}

rsproto {
    protoSourcePath = "src/main/proto/"
    generationOutputPath = "generated/rsproto/kotlin"

    clientGeneration = true
    serverGeneration = false
}

tasks.withType<Test> {
    useJUnitPlatform()
}