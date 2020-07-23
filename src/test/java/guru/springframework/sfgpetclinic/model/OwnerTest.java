package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit dependent assertions examples
 */
class OwnerTest implements ModelTest {

    @Test
    void dependentAssertions() {
        // given
        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("West");
        owner.setTelephone("13112312421");

        // then
        assertAll("Test props set",
                () -> assertAll("Person properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "First name did not match"),
                        () -> assertEquals("Buck", owner.getLastName(), "Last name did not match")),
                () -> assertAll("Owner properties",
                        () -> assertEquals("West", owner.getCity(), "City did not match"),
                        () -> assertEquals("13112312421", owner.getTelephone(), "Telephone did not match")
                ));

    }
}