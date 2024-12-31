package org.task2;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportPBTTest {
    // Test per valori validi di peso e altezza
    @Label("Test per valori validi di peso e altezza; è probabile che il range valido di valori dia un BMI fuori range.")
    @Property
    void calcolaBMI_valoriValidi(@ForAll @DoubleRange(min = 2.40, max = 500.0) double peso,
                                 @ForAll @DoubleRange(min = 0.40, max = 2.50) double altezza) {
        double bmi = 0.00;
        try {
            File validoFile = new File("./risultati/bmi_valido.csv");
            if (!validoFile.exists() || validoFile.length() == 0) {
                try (FileWriter writer = new FileWriter(validoFile, true)) {
                    // Scrivi l'intestazione se il file è vuoto o non esiste
                    writer.append("peso,altezza,bmi\n");
                }
            }

            try (FileWriter writer = new FileWriter(validoFile, true)) {
                bmi = BMIUtils.calcolaBMI(peso, altezza);
                // Controllo che il BMI sia calcolato correttamente nel range previsto
                Assertions.assertTrue(bmi >= 10 && bmi <= 80, "BMI non è nel range valido");

                // Scrivi i dati nel file
                writer.append(String.valueOf(peso)).append(",")
                        .append(String.valueOf(altezza)).append(",")
                        .append(String.valueOf(bmi)).append("\n");
            }
        } catch (IllegalArgumentException e) {
            // Gestione dei casi in cui BMI è fuori dal range realistico
            Assertions.assertTrue(true, "Eccezione prevista per valori non validi");

            double bmiErrato = peso / (altezza * altezza);
            double ceiledBMI =  Math.ceil(bmiErrato * 100.0) / 100.0;

            File invalidoFile = new File("./risultati/bmi_invalido.csv");
            if (!invalidoFile.exists() || invalidoFile.length() == 0) {
                try (FileWriter errorWriter = new FileWriter(invalidoFile, true)) {
                    // Scrivi l'intestazione se il file è vuoto o non esiste
                    errorWriter.append("peso,altezza,bmi\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            // Salva i casi errati (valori non validi)
            try (FileWriter errorWriter = new FileWriter(invalidoFile, true)) {
                errorWriter.append(String.valueOf(peso)).append(",")
                        .append(String.valueOf(altezza)).append(",")
                        .append(String.valueOf(ceiledBMI)).append("\n");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}