package guru.springframework.sfgpetclinic.junit5examples.controllers;

import guru.springframework.sfgpetclinic.controllers.IndexController;
import guru.springframework.sfgpetclinic.controllers.ValueNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit assertions examples, AssertJ example, test exception throw
 */
class IndexControllerTest implements ControllerTest {

    private static final String INDEX = "index";
    private static final String WRONG_VIEW_MSG = "Wrong view returned";

    private IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test proper view name is returned for index page")
    @Test
    void index() {
        String index = controller.index();
        assertEquals(INDEX, index, WRONG_VIEW_MSG);
        // message processing with lambda will be executed only if assert fails
        assertEquals(INDEX, index, () -> WRONG_VIEW_MSG);
        // AssertJ
        assertThat(index).isEqualTo(INDEX);
    }

    @DisplayName("Test value not found exception")
    @Test
    void oopsHandler() {
        assertThrows(ValueNotFoundException.class, () -> controller.oopsHandler());
    }
}