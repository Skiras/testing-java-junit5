package guru.springframework.sfgpetclinic.junit5examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionTest {

    @Test
    void testAssumptionTrue() {
        // aborts test execution if assumption is not true
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }
}
