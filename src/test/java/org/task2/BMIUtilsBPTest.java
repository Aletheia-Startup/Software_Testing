package org.task2;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import org.junit.jupiter.api.Assertions;

class BMIUtilsBPTest {

    // Test per valori validi di peso e altezza
    @Label("Test per valori validi di peso e altezza")
    @Property
    void calcolaBMI_valoriValidi(@ForAll @DoubleRange(min = 2.40, max = 500.0) double peso,
                                 @ForAll @DoubleRange(min = 0.4, max = 2.50) double altezza) {
        double bmi = BMIUtils.calcolaBMI(peso, altezza);

        if(bmi <= 0 || bmi > 80.0){
            boolean inRange = true;
        }

        // Controllo che il BMI sia calcolato correttamente nel range previsto
        Assertions.assertTrue(inRange, "BMI non è nel range valido");
    }


    @Label("Test per valori di peso non validi (minori di 1.50 o maggiori di 500)")
    @Property
    void calcolaBMI_pesoNonValido(@ForAll @DoubleRange(min = 0.0, max = 2.50) double peso,
                                  @ForAll @DoubleRange(min = 0.41, max = 2.70) double altezza) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
    }

    @Label("Test per valori di peso elevati")
    @Property
    void calcolaBMI_pesoTroppoElevato(@ForAll @DoubleRange(min = 500.01, max = 1000.0) double peso,
                                      @ForAll @DoubleRange(min = 0.41, max = 2.70) double altezza) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
    }


    @Label("Test per valori di altezza non validi (minori di 0.40 o maggiori di 2.70)")
    @Property
    void calcolaBMI_altezzaNonValida(@ForAll @DoubleRange(min = 0.0, max = 0.40) double altezza,
                                     @ForAll @DoubleRange(min = 1.51, max = 500.0) double peso) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
    }

    @Label("Test per valori d'altezza elevati")
    @Property
    void calcolaBMI_altezzaTroppoElevata(@ForAll @DoubleRange(min = 2.71, max = 5.0) double altezza,
                                         @ForAll @DoubleRange(min = 1.51, max = 500.0) double peso) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
    }


    @Label("Test per valori validi di peso e altezza ma che producono BMI non realistici")
    @Property
    void calcolaBMI_BMINonRealistico(@ForAll @DoubleRange(min = 1.51, max = 500.0) double peso,
                                     @ForAll @DoubleRange(min = 0.41, max = 2.70) double altezza) {
        // Forziamo il BMI fuori dal range per testare la validazione
        double bmi = peso / (altezza * altezza);
        if (bmi < 10.0 || bmi > 80.0) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
        }
    }
}
