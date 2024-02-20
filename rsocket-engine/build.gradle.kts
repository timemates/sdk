plugins {
    id(libs.plugins.conventions.multiplatform.library.get().pluginId)
    alias(libs.plugins.kotlinx.serialization)
    //alias(libs.plugins.timemates.rsproto)
}

kotlin {
    sourceSets {
        commonMain {
            kotlin.srcDirs("src/main/kotlin", "build/generated/rsproto/kotlin")
        }
    }
}

dependencies {
    commonMainImplementation(projects.sdk)

    commonMainImplementation(libs.timemates.rsproto.common)
    commonMainImplementation(libs.timemates.rsproto.client)

    commonMainImplementation(libs.kotlinx.serialization)
    commonMainImplementation(libs.kotlinx.datetime)
    commonMainImplementation(libs.rsocket.client)
}

//rsproto {
//    protoSourcePath = "src/main/proto/"
//    generationOutputPath = "generated/rsproto/kotlin"
//
//    clientGeneration = true
//    serverGeneration = false
//}

tasks.withType<Test> {
    useJUnitPlatform()
}

mavenPublishing {
    coordinates(
        groupId = "org.timemates.sdk",
        artifactId = "rsocket-engine",
        version = System.getenv("LIB_VERSION") ?: return@mavenPublishing,
    )

    pom {
        name.set("TimeMates RSocket Engine")
        description.set("Multiplatform implementation of the API using rsproto.")
    }
}