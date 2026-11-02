package consumer;

import io.github.leanish.test.publishsplit.Greeting;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        System.out.println(Greeting.of("consumer"));
    }
}
