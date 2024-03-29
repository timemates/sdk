enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.timemates.org/dev")
        maven("https://maven.timemates.org/releases")
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.y9vad9.com")
        maven("https://maven.timemates.org/dev")
        maven("https://maven.timemates.org/releases")
    }
}

rootProject.name = "timemates-sdk"

includeBuild("build-conventions")

include(":sdk")
include(":rsocket-engine")
