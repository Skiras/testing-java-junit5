package guru.springframework.sfgpetclinic.junit5examples.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

/**
 * Working with @Tag example, non-static before all (Lifecycle.PER_CLASS)
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("controller")
public interface ControllerTest {

    @BeforeAll
    default void beforeAll() {
        System.out.println("Before all method from interface");
    }
}
