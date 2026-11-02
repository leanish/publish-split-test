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

val resolvedFiles by tasks.registering {
    val files = configurations.runtimeClasspath.map { it.incoming.artifacts.artifacts.map { a -> a.file.name } }
    doLast { files.get().forEach { logger.lifecycle("resolved: $it") } }
}
