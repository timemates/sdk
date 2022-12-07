# 🧱 Tomadoro SDK

The repository with source code of tomadoro multiplatform API library on Kotlin.

## 🔭 Setup

If you want to use this library, follow next instructions:

### Gradle

To add tomadoro SDK in your gradle project, you should do next:

```kotlin
repositories {
    maven("https://maven.y9vad9.com")
}

dependencies {
    commonMainImplementation("org.tomadoro:sdk:$version") // for multiplatform
    implementation("org.tomadoro:sdk:$version") // for JVM project
}
```

`$version` you can find at the top of the README file or in the releases.
