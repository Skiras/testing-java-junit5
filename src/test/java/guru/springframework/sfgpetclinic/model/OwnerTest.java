package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerTest implements ModelTests {

    @Test
    void groupedAssertions() {
        // given
        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("West");
        owner.setTelephone("13112312421");

        // then
        assertAll("Test props set",
                () -> assertAll("Person props",
                        () -> assertEquals("Joe", owner.getFirstName(), "First name did not match"),
                        () -> assertEquals("Buck", owner.getLastName())),
                () -> assertAll("Owner props",
                        () -> assertEquals("West", owner.getCity(), "City did not match"),
                        () -> assertEquals("13112312421", owner.getTelephone())
                ));

    }
}