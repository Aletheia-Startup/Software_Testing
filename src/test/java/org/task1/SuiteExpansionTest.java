package org.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SuiteExpansionTest {

    @DisplayName("Combinazione di possibili valori.")
    @ParameterizedTest
    @MethodSource("provideReplaceTestCases")
    void testSimpleReplace(String original, String target, String replacement, String expected) {
        Assertions.assertEquals(expected, StringUtils.replace(original, target, replacement));
    }

    static Stream<Arguments> provideReplaceTestCases() {
        return Stream.of(
                // Original senza spazi
                Arguments.of("helloworld", "world", "everyone", "helloeveryone"),
                // Original e target contenente numeri
                Arguments.of("JUnit5 Java Framework", "5", "Testing", "JUnitTesting Java Framework"),
                // target contenente spazi
                Arguments.of("JUnit5 Java Framework", " Java ", "Testing", "JUnit5TestingFramework")
        );
    }

}


