plugins {
    `java-gradle-plugin`
    `maven-publish`
    // Like java-conventions, for the publications and classifiers it adds; `publishPlugins` (the Portal) never runs.
    id("com.gradle.plugin-publish")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
    withSourcesJar()
    withJavadocJar()
}

// Like java-conventions: a plugin JAR Gradle can load on JDK 17 and later.
tasks.compileJava {
    options.release.set(17)
}

gradlePlugin {
    website.set("https://github.com/leanish/publish-split-test")
    vcsUrl.set("https://github.com/leanish/publish-split-test")
    plugins {
        create("publishSplitTest") {
            id = "io.github.leanish.test.publish-split-test"
            implementationClass = "io.github.leanish.test.publishsplit.plugin.PublishSplitTestPlugin"
            displayName = "Publish split test"
            description = "Disposable fixture for testing a split publishing workflow."
            tags.set(listOf("test"))
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/leanish/publish-split-test")
        }
    }
}
