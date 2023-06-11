plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.library.publish)
}

kotlin {
    jvm()

    explicitApi()
}

group = "io.timemates"
version = libs.versions.library.version.get()

dependencies {
    commonMainImplementation(libs.kotlinx.datetime)
    commonMainImplementation(libs.kotlinx.coroutines)
}

deployLibrary {
    ssh(tag = "maven.timemates.io") {
        host = System.getenv("TIMEMATES_SSH_HOST")
        user = System.getenv("TIMEMATES_SSH_USER")
        password = System.getenv("TIMEMATES_SSH_PASSWORD")
        deployPath = System.getenv("TIMEMATES_SSH_DEPLOY_PATH")

        group = "io.timemates"
        componentName = "kotlin"
        artifactId = "sdk"
        name = "sdk"

        description = "TimeMates SDK"

        version = libs.versions.library.version.get()
    }
}