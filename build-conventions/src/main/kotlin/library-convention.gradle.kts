plugins {
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    pom {
        url.set("https://github.com/timemates/sdk")
        inceptionYear.set("2023")

        licenses {
            license {
                name.set("The MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("y9vad9")
                name.set("Vadym Yaroshchuk")
                url.set("https://github.com/y9vad9/")
            }
        }

        scm {
            url.set("https://github.com/timemates/sdk")
            connection.set("scm:git:git://github.com/timemates/sdk.git")
            developerConnection.set("scm:git:ssh://git@github.com/timemates/sdk.git")
        }

        issueManagement {
            system.set("GitHub Issues")
            url.set("https://github.com/timemates/sdk/issues")
        }
    }
}

publishing {
    repositories {
        maven {
            name = "TimeMates"

            url = uri(
                "sftp://${System.getenv("SSH_HOST")}:22/${System.getenv("SSH_DEPLOY_PATH")}"
            )

            credentials {
                username = System.getenv("SSH_USER")
                password = System.getenv("SSH_PASSWORD")
            }
        }
    }
}