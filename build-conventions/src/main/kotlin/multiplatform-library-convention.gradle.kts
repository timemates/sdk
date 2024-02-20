import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
    id("multiplatform-convention")
    id("library-convention")
}

kotlin {
    explicitApi = ExplicitApiMode.Strict
}