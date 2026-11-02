package io.github.leanish.test.publishsplit;

/** The one thing the fixture library does, so a consumer can call it. */
public final class Greeting {

    private Greeting() {
    }

    /**
     * Greets someone.
     *
     * @param name who to greet
     * @return the greeting
     */
    public static String of(String name) {
        return "Hello, " + name;
    }
}
