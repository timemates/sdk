pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }

    plugins {
        kotlin("plugin.serialization") version "1.7.20"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.y9vad9.com")
    }
}

rootProject.name = "tomadoro-sdk"

includeBuild("build-logic/dependencies")
includeBuild("build-logic/configuration")
includeBuild("build-logic/service-deploy")
includeBuild("build-logic/library-deploy")

include(":sdk")
