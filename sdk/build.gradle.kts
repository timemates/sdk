import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Deps.Plugins.Configuration.Kotlin.Mpp)
    id(Deps.Plugins.Android.Library)
    id(Deps.Plugins.Serialization.Id)
}

kotlin {
    js {
        browser()
    }
    jvm()
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Libs.Ktor.Client.Core)
                implementation(Deps.Libs.Ktor.Client.ContentNegotiation)
                implementation(Deps.Libs.Ktor.Client.CallLogging)
                implementation(Deps.Libs.Ktor.Client.WebSockets)
                implementation(Deps.Libs.Ktor.Client.WebSocketsJson)
                implementation(Deps.Libs.Kotlinx.Serialization)
                implementation(Deps.Libs.Ktor.Json)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(Deps.Libs.Ktor.Client.Js)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(Deps.Libs.Ktor.Client.Cio)
            }
        }
    }

    explicitApi()
}

android {
    compileSdk = Deps.compileSdkVersion

    sourceSets {
        getByName("main") {
            manifest {
                srcFile("src/androidMain/AndroidManifest.xml")
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        languageVersion = "1.8"
        freeCompilerArgs = listOf("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}