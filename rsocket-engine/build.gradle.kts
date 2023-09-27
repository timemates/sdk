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