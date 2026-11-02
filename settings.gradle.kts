pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    // The same plugin versions as sqs-codec (java-conventions) and java-conventions (plugin-publish).
    plugins {
        id("io.github.leanish.java-conventions") version "0.6.3"
        id("com.gradle.plugin-publish") version "2.2.1"
    }
}

rootProject.name = "publish-split-test-root"

// Project names are the artifact IDs; java-conventions also derives the GitHub Packages repository from them, so the
// library's name matches this repository.
include("publish-split-test", "publish-split-test-plugin")
