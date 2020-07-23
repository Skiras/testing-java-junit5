package guru.springframework.sfgpetclinic.junit5examples;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Disabled
public class TimeoutTest {

    @Test
    void testTimeout() {
        // single thread
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("I got here");
        });
    }

    @Test
    void testTimeoutPreemptively() {
        // spawns separate thread and terminates it after duration time
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("I don't got here because of preemptively");
        });
    }
}
