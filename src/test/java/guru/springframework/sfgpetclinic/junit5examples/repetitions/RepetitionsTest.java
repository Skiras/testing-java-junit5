package guru.springframework.sfgpetclinic.junit5examples.repetitions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class RepetitionsTest implements RepeatedTestDi {

    @DisplayName("My repeated test")
    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
    void myRepeatedTest() {

    }

    @RepeatedTest(5)
    void myRepeatedTestWithDi(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + " : " + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("My repeated test")
    void myRepeatedTestWithDiInterface() {

    }
}
