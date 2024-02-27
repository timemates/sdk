plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm {
        jvmToolchain(11)
    }
    js {
        browser()
        nodejs()
    }
    iosArm64()
    iosX64()
    iosSimulatorArm64()
}