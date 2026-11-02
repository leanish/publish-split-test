plugins {
    java
    id("io.github.leanish.test.publish-split-test")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

dependencies {
    implementation("io.github.leanish.test:publish-split-test:${providers.gradleProperty("testVersion").get()}")
}

// Runs code from the published library and lists the files Gradle downloaded (timestamped names for a SNAPSHOT).
val greet by tasks.registering(JavaExec::class) {
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("consumer.Main")
}

// The component Gradle resolved: for a SNAPSHOT its display name carries the unique timestamp and build.
val resolvedFiles by tasks.registering {
    val resolved = configurations.runtimeClasspath.map {
        it.incoming.artifacts.artifacts.map { a -> "${a.id.componentIdentifier.displayName} ${a.file.name}" }
    }
    doLast { resolved.get().forEach { logger.lifecycle("resolved: $it") } }
}
