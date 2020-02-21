package guru.springframework.sfgpetclinic.junit5examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class ExecutingOnConditionTest {

    @EnabledOnOs(OS.MAC)
    @Test
    void testOnMac() {

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testOnWindows() {

    }
}
