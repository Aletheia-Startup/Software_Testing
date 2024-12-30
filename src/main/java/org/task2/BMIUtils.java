package org.task2;

public class BMIUtils {

    private BMIUtils() { }

    /**
     * Calcola l'Indice di Massa Corporea (BMI) dato il peso e l'altezza.
     *
     * @param peso    Peso in chilogrammi.
     * @param altezza Altezza in metri.
     * @return Il BMI calcolato e arrotondato a due decimali.
     * @throws IllegalArgumentException se il peso è <= 1.50 o altezza è <= 0.40 i valori non sono realistici.
     */
    public static double calcolaBMI(double peso, double altezza) {
        // Controllo dei valori non validi per peso e altezza
        if (peso < 1.50 || peso > 500) {
            throw new IllegalArgumentException("Il peso deve avere valori reali");
        }

        if (altezza < 0.40 || altezza > 2.70) {
            throw new IllegalArgumentException("L'altezza deve avere valori reali");
        }

        // Calcolo del BMI
        double bmi = peso / (altezza * altezza);
        double ceiledBMI = Math.ceil(bmi * 100.0) / 100.0;

        // Limiti per BMI (range ragionevole)
        if (ceiledBMI < 10 || ceiledBMI > 80) {
            throw new IllegalArgumentException("Il BMI calcolato non è realistico.");
        }
        return ceiledBMI;
    }
}



