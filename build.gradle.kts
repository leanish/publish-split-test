// A disposable fixture for testing a publishing workflow that never runs Gradle with a write token: both projects
// publish to a shared file repository (`Staging`), which a separate job uploads to GitHub Packages.

val publishVersion = providers.gradleProperty("publishVersion").orElse("0.0.0-SNAPSHOT")
val stagingDir = layout.buildDirectory.dir("publication-staging")

subprojects {
    group = "io.github.leanish.test"
    version = publishVersion.get()

    plugins.withId("maven-publish") {
        extensions.configure<PublishingExtension> {
            repositories {
                maven {
                    name = "Staging"
                    url = uri(stagingDir)
                }
            }
        }
    }
}
