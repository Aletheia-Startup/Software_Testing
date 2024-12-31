package org.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

public class BMIUtilsTest {

    @DisplayName("Test per il costruttore")
    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<BMIUtils> constructor = BMIUtils.class.getDeclaredConstructor();
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
                // T1 - Valori Validi
                Arguments.of(80.00, 1.87),
                // T13 - Limite Peso
                Arguments.of(3.00, 0.40),
                // T14 - Limite Peso Superiore
                Arguments.of(500, 2.50),
                // T15 Limite Inferiore BMI
                Arguments.of( 48.4, 2.20),
                // T16 Limite Superiore BMI
                Arguments.of(150.15, 1.37)
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
                // T12 - Limite Altezza e BMI non realistico
                Arguments.of(1.50, 0.40),
                // T2 - Caso in cui il peso non sia valido
                Arguments.of(0.50, 1.87),
                // T3 - Caso in cui l'altezza non sia valida
                Arguments.of(55.00, 0.25),
                // T4 - Caso in cui il BMI non è valido - Superiore
                Arguments.of(180.00, 1.45),
                // T5 - Caso in cui il BMI non è valido - Inferiore
                Arguments.of(30.00, 2.15),
                // Casi di test rilevati nel PIT
                // T8 - Caso sotto limite peso
                Arguments.of(2.39, 1.87),
                // T9 -  Caso sotto limite altezza
                Arguments.of(80.00, 0.39),
                // T6 - Caso in cui si superi il peso massimo
                Arguments.of(3000.00,1.90),
                // T7 - Caso in cui si superi l'altezza massima
                Arguments.of(100.00, 5.27),
                // T10 - BMI Fuori limite inferiore
                Arguments.of(75.9, 2.77),
                // T11 - BMI Fuori limite superiore
                Arguments.of(150.0, 1.36)

        );
    }
}

