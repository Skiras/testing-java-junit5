package guru.springframework.sfgpetclinic.junit5examples.parameterized;

import guru.springframework.sfgpetclinic.model.OwnerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.params.ParameterizedTest.DEFAULT_DISPLAY_NAME;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

public class ParameterizedTests {

    @DisplayName("Value source test -")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " " + DEFAULT_DISPLAY_NAME)
    @ValueSource(strings = {"spring", "framework", "guru"})
    void testValueSource(String value) {
        System.out.println(value);
    }

    @DisplayName("Enum source test - ")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " " + DEFAULT_DISPLAY_NAME)
    @EnumSource(OwnerType.class)
    void testEnumSource(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("CSV source test - ")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " " + DEFAULT_DISPLAY_NAME)
    @CsvSource({
            "FL, 1, 1",
            "OH, 2, 2",
            "MI, 3, 3"
    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " - " + val1 + " - " + val2);
    }

    @DisplayName("CSV file source test - ")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " " + DEFAULT_DISPLAY_NAME)
    @CsvFileSource(resources = "/input.csv", /* skips header */ numLinesToSkip = 1)
    void csvFileInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " - " + val1 + " - " + val2);
    }

    @DisplayName("Method source test - ")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " " + DEFAULT_DISPLAY_NAME)
    @MethodSource("getArgs")
    void testMethodSource(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    private static Stream<Arguments> getArgs() {
        return Stream.of(OwnerType.values()).map(Arguments::of);
    }

    @DisplayName("Data provider source test - ")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " " + DEFAULT_DISPLAY_NAME)
    @ArgumentsSource(DataProvider.class)
    void testDataProviderSource(OwnerType ownerType) {
        System.out.println(ownerType);
    }
}
