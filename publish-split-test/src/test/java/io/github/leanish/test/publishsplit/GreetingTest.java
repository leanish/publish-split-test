package io.github.leanish.test.publishsplit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GreetingTest {

    @Test
    void greetsByName() {
        assertThat(Greeting.of("leanish")).isEqualTo("Hello, leanish");
    }
}
