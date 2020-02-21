package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

// working with tags example, non-static before all (Lifecycle.PER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("controllers")
public interface ControllerTests {

    @BeforeAll
    default void beforeAll() {
        System.out.println("before all from interface");
    }
}
