package guru.springframework.sfgpetclinic.junit5examples.repetitions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public interface RepeatedTestDi {

    @BeforeEach
    default void testInfo(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("before each" + testInfo.getDisplayName() + " : " + repetitionInfo.getCurrentRepetition());
    }
}
