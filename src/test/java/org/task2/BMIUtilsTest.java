package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.task1.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

public class BMIUtilsTest {

    @DisplayName("Test per il costruttore")
    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<StringUtils> constructor = StringUtils.class.getDeclaredConstructor();
        Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        Assertions.assertDoesNotThrow(() -> constructor.newInstance());
    }

    @DisplayName("Multipli possibili valori in input - No Exception")
    @ParameterizedTest
    @MethodSource("provideCorrectValues")
    void testCorrectValues(double peso, double altezza) {
        double bmi = Math.ceil(peso / (altezza * altezza)*100.0)/100.0;
        Assertions.assertEquals(bmi, BMIUtils.calcolaBMI(peso,altezza));
    }

    static Stream<Arguments> provideCorrectValues() {
        return Stream.of(
                // T2 - Caso in cui il peso non sia valido
                Arguments.of(80.00, 1.87),
                // T3 - Limite Altezza
                Arguments.of(1.50, 0.40),
                // T3 - Limite Peso
                Arguments.of(3.00, 0.40)
        );
    }

    @DisplayName("Multipli possibili valori in input - Exception")
    @ParameterizedTest
    @MethodSource("provideMultipleValues")
    void testMultipleValues(double peso, double altezza) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
    }

    static Stream<Arguments> provideMultipleValues() {
        return Stream.of(
                // T2 - Caso in cui il peso non sia valido
                Arguments.of(0.50, 1.87),
                // T3 - Caso in cui l'altezza non sia valida
                Arguments.of(55.00, 0.25),
                // T4 - Caso in cui il BMI non è valido - Superiore
                Arguments.of(180.00, 1.45),
                // T5 - Caso in cui il BMI non è valido - Inferiore
                Arguments.of(30.00, 2.15),
                // Casi di test rilevati nel PIT
                // Caso sotto limite altezza
                Arguments.of(1.49, 1.87),
                // Caso sotto limite peso
                Arguments.of(80.00, 0.39),
                // Caso in cui si superi il peso massimo
                Arguments.of(3000.00,1.90),
                // Caso in cui si superi l'altezza massima
                Arguments.of(100.00, 5.27)
        );
    }
}
