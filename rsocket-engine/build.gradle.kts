plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.library.publish)
}

kotlin {
    jvm()
    jvmToolchain(17)

    explicitApi()
}

dependencies {
    commonMainImplementation(projects.sdk)

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

tasks.withType<Test> {
    useJUnitPlatform()
}