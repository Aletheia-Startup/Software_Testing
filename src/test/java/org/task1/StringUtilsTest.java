package org.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class StringUtilsTest {

    @DisplayName("Combinazione di possibili valori.")
    @ParameterizedTest
    @MethodSource("provideReplaceTestCases")
    void testSimpleReplace(String original, String target, String replacement, String expected) {
        Assertions.assertEquals(expected, StringUtils.replace(original, target, replacement));
    }

    static Stream<Arguments> provideReplaceTestCases() {
        return Stream.of(
                // T8 - Caso con valori corretti e sostituzioni effettuate
                Arguments.of("Ciao mondo, mondo bello!", "mondo", "universo", "Ciao universo, universo bello!"),
                // T9 - Caso in cui il target non è presente nella stringa originale
                Arguments.of("Ciao mondo!", "galassia", "universo", "Ciao mondo!"),
                // T6 - Caso in cui original è vuoto
                Arguments.of("", "mondo", "universo", ""),
                // T10 - Caso con target case sensitive
                Arguments.of("Ciao mondo, Mondo bello!", "mondo", "universo", "Ciao universo, Mondo bello!"),
                // T11 - Caso con original con caratteri speciali
                Arguments.of("Ciao@mondo#mondo", "mondo", "$", "Ciao@$#$"),
                // T7 - Caso con il replacement vuoto
                Arguments.of("Integrazione e test di sistemi software", "test", "", "Integrazione e  di sistemi software"),
                // T12 -Caso in cui replacement e target sono uguali
                Arguments.of("Integrazione e test di sistemi software", "software", "software", "Integrazione e test di sistemi software"),
                // T13 - Caso con stringa di lunghezza 1
                Arguments.of("m", "m", "M", "M"),
                // T14 - Caso con stringhe molto lunghe
                Arguments.of("a".repeat(1_000_000), "a", "b", "b".repeat(1_000_000))
        );
    }

    @DisplayName("1: Target nullo, 2: Original nullo, 3: Replacement nullo, 4: Parametri nulli")
    @ParameterizedTest
    @MethodSource("provideNullValuesTestCases")
    void testValuesNull(String original, String target, String replacement) {
        Assertions.assertThrows(NullPointerException.class, () -> StringUtils.replace(original, target, replacement));
    }

    static Stream<Arguments> provideNullValuesTestCases() {
        return Stream.of(
                // T2 - Caso con target nullo
                Arguments.of("Ciao mondo, mondo bello!", null, "universo"),
                // T1 - Caso con original nullo
                Arguments.of(null, "mondo", "universo"),
                // T4 - Caso con replacement nullo
                Arguments.of("Ciao mondo, mondo bello!", "mondo", null),
                // T5 - Caso in cui original, target e replacement sono nulli
                Arguments.of(null, null, null)
        );
    }

    @DisplayName("1: Target vuoto")
    @ParameterizedTest
    @CsvSource({
            // T3 - Caso con target vuoto
            "'Ciao mondo, mondo bello!', '' , 'universo'",
    })
    void testTargetEmpty(String original, String target, String replacement) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> StringUtils.replace(original, target, replacement));
    }
}