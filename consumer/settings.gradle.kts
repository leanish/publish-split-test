// A separate build that consumes what the workflow published, from GitHub Packages only (no mavenLocal, no staging,
// no composite build). `-PrepoUrl` points it at another Maven repository (a local staging directory) for a dry run.
val repoUrl = providers.gradleProperty("repoUrl").orElse("https://maven.pkg.github.com/leanish/publish-split-test")

pluginManagement {
    repositories {
        maven {
            name = "Fixture"
            url = uri(providers.gradleProperty("repoUrl").orElse("https://maven.pkg.github.com/leanish/publish-split-test").get())
            if (url.scheme == "https") {
                credentials {
                    username = providers.environmentVariable("GITHUB_ACTOR").get()
                    password = providers.environmentVariable("GITHUB_TOKEN").get()
                }
            }
        }
        gradlePluginPortal()
    }
    plugins {
        id("io.github.leanish.test.publish-split-test") version providers.gradleProperty("testVersion").get()
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            name = "Fixture"
            url = uri(repoUrl.get())
            if (url.scheme == "https") {
                credentials {
                    username = providers.environmentVariable("GITHUB_ACTOR").get()
                    password = providers.environmentVariable("GITHUB_TOKEN").get()
                }
            }
            // `gradle` accepts only Gradle Module Metadata (fails if the .module is missing); `pom` ignores it.
            metadataSources {
                when (providers.gradleProperty("metadataSource").orElse("gradle").get()) {
                    "gradle" -> gradleMetadata()
                    "pom" -> {
                        mavenPom()
                        ignoreGradleMetadataRedirection()
                    }
                    else -> throw GradleException("metadataSource must be gradle or pom")
                }
            }
            content { includeGroup("io.github.leanish.test") }
        }
        mavenCentral()
    }
}

rootProject.name = "publish-split-test-consumer"
