package guru.springframework.sfgpetclinic.junit5examples.model;

import guru.springframework.sfgpetclinic.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit grouped assertions examples
 */
class PersonTest implements ModelTest {

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person(1L, "Joe", "Buck");
    }

    @Test
    void groupedAssertions() {
        assertAll("Test person properties set",
                () -> assertEquals("Joe", person.getFirstName()),
                () -> assertEquals("Buck", person.getLastName()),
                () -> assertEquals(Long.valueOf(1L), person.getId()));
    }

    @Test
    void groupedAssertionsMsg() {
        assertAll("Test person properties set",
                () -> assertEquals("Joe", person.getFirstName(), "First name failed"),
                () -> assertEquals("Buck", person.getLastName(), "Last name failed"),
                () -> assertEquals(Long.valueOf(1L), person.getId(), "Id failed"));
    }
}