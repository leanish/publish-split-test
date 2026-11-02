package io.github.leanish.test.publishsplit.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/** Registers one task, so a consumer can show the plugin was resolved by its id and applied. */
public final class PublishSplitTestPlugin implements Plugin<Project> {

    /** Creates the plugin; Gradle instantiates it. */
    public PublishSplitTestPlugin() {
    }

    @Override
    public void apply(Project project) {
        project.getTasks().register("publishSplitHello", task -> task.doLast(t -> t.getLogger().lifecycle("publish-split-test plugin applied")));
    }
}
