// A disposable fixture for testing a publishing workflow that never runs Gradle with a write token: both projects
// publish to a shared file repository (`Staging`), which a separate job uploads to GitHub Packages. The version is
// the root project's (gradle.properties), as the reusable workflow reads it.

val stagingDir = layout.buildDirectory.dir("publication-staging")

subprojects {
    group = "io.github.leanish.test"
    version = rootProject.version

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
