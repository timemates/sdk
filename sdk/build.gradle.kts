plugins {
    id(libs.plugins.conventions.multiplatform.library.get().pluginId)
}

group = "org.timemates.sdk"

dependencies {
    commonMainImplementation(libs.kotlinx.datetime)
    commonMainImplementation(libs.kotlinx.coroutines)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

mavenPublishing {
    coordinates(
        groupId = "org.timemates.sdk",
        artifactId = "sdk",
        version = System.getenv("LIB_VERSION") ?: return@mavenPublishing,
    )

    pom {
        name.set("TimeMates SDK")
        description.set("Multiplatform SDK for TimeMates API.")
    }
}