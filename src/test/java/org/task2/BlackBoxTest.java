package org.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class BlackBoxTest {

    @DisplayName("Caso semplice in cui tutti i valori sono corretti")
    @Test
    public void correctCaseTest(){
        double peso = 80.00; double altezza = 1.87;
        double bmi = Math.ceil(peso / (altezza * altezza)*100.0)/100.0;
        Assertions.assertEquals(bmi, BMIUtils.calcolaBMI(peso,altezza));
    }

    @DisplayName("Caso in cui il peso non sia valido")
    @Test
    public void throwsExceptionPesoTest(){
        double peso = 0.50; double altezza = 1.87;
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso,altezza));
    }

    @DisplayName("Caso in cui l'altezza non sia valida")
    @Test
    public void throwsExceptionAltezzaTest(){
        double peso = 55.00; double altezza = 0.25;
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso,altezza));
    }

    @DisplayName("Caso in cui il BMI non è valido -  Superiore")
    @Test
    public void throwsExceptionOnOverBMITest(){
        double peso = 180.00; double altezza = 1.45;
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso,altezza));
    }

    @DisplayName("Caso in cui il BMI non è valido - Inferiore")
    @Test
    public void throwsExceptionOnUnderBMITest(){
        double peso = 30.00; double altezza = 2.15;
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso,altezza));
    }
}