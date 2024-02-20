plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm {
        jvmToolchain(11)
    }
    js {
        browser()
    }
}