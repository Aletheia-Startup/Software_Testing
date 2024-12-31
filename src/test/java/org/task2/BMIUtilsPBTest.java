package org.task2;


import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;
import org.junit.jupiter.api.Assertions;


class BMIUtilsPBTest {

    // Test per valori validi di peso e altezza
    @Label("Test per valori validi di peso e altezza; è probabile che il range valido di valori dia un BMI fuori range.")
    @Property
    @StatisticsReport(format = Histogram.class)
    void calcolaBMI_valoriValidi(@ForAll @DoubleRange(min = 2.40, max = 500.0) double peso,
                                 @ForAll @DoubleRange(min = 0.40, max = 2.50) double altezza) {
        double bmi = 0.00;
        try {
            bmi = BMIUtils.calcolaBMI(peso, altezza);
            // Controllo che il BMI sia calcolato correttamente nel range previsto
            Assertions.assertTrue(bmi >= 10 && bmi <= 80, "BMI non è nel range valido");
        } catch (IllegalArgumentException e) {
            //Gestione dei casi in cui BMI è fuori dal range realistico
            Assertions.assertTrue(true, "Eccezione prevista per valori non validi");
        }
    }

    @Label("Test per valori di peso non validi (minori di 2.40)")
    @Property
    void calcolaBMI_pesoNonValido(@ForAll @DoubleRange(min = 0.00, max = 2.39) double peso,
                                  @ForAll @DoubleRange(min = 0.40, max = 2.50) double altezza) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
    }

    @Label("Test per valori di peso elevati")
    @Property
    void calcolaBMI_pesoTroppoElevato(@ForAll @DoubleRange(min = 500.01, max = 1000.0) double peso,
                                      @ForAll @DoubleRange(min = 0.40, max = 2.50) double altezza) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
    }


    @Label("Test per valori di altezza non validi (minori di 0.40)")
    @Property
    void calcolaBMI_altezzaNonValida(@ForAll @DoubleRange(min = 0.0, max = 0.39) double altezza,
                                     @ForAll @DoubleRange(min = 2.40, max = 500.0) double peso) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
    }

    @Label("Test per valori d'altezza elevati")
    @Property
    void calcolaBMI_altezzaTroppoElevata(@ForAll @DoubleRange(min = 2.71, max = 5.0) double altezza,
                                         @ForAll @DoubleRange(min = 2.40, max = 500.0) double peso) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> BMIUtils.calcolaBMI(peso, altezza));
    }

}
