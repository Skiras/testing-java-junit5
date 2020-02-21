package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest implements ModelTests {

    @Test
    void groupedAssertions() {
        // given
        Person person = new Person(1L, "Joe", "Buck");

        // then
        assertAll("Test Props set",
                () -> assertEquals("Joe", person.getFirstName()),
                () -> assertEquals("Buck", person.getLastName()),
                () -> assertEquals(Long.valueOf(1L), person.getId()));
    }

    @Test
    void groupedAssertionsMsg() {
        // given
        Person person = new Person(1L, "Joe", "Buck");

        // then
        assertAll("Test Props set",
                () -> assertEquals("Joe", person.getFirstName(), "First name failed"),
                () -> assertEquals("Buck", person.getLastName(), "Last name failed"),
                () -> assertEquals(Long.valueOf(1L), person.getId(), "Id failed"));
    }
}