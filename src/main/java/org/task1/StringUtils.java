package org.task1;

public class StringUtils {

    private StringUtils() { }

    /**
     * Sostituisce tutte le occorrenze di una sottostringa (`target`) con un'altra sottostringa (`replacement`)
     * all'interno di una stringa originale (`original`).
     *
     * @param original    La stringa originale in cui effettuare la sostituzione.
     * @param target      La sottostringa da sostituire.
     * @param replacement La nuova sottostringa con cui sostituire.
     * @return Una nuova stringa con tutte le sostituzioni effettuate.
     * @throws NullPointerException     Se `original`, `target` o `replacement` sono null.
     * @throws IllegalArgumentException Se `target` è vuoto.
     */

    public static String replace(String original, String target, String replacement) {
        if (original == null || target == null || replacement == null) {
            throw new NullPointerException("original, target e replacement non devono essere null");
        }
        if (target.isEmpty()) {
            throw new IllegalArgumentException("target non deve essere vuoto");
        }

        // Usa un StringBuilder per costruire il risultato
        StringBuilder result = new StringBuilder();
        int start = 0;
        int index;

        // Cerca tutte le occorrenze di target e le sostituisce con replacement
        while ((index = original.indexOf(target, start)) != -1) {
            result.append(original, start, index); // Aggiunge la parte prima di target
            result.append(replacement);           // Aggiunge replacement
            start = index + target.length();      // Aggiorna il punto di partenza
        }

        // Aggiunge la parte rimanente della stringa originale
        result.append(original.substring(start));
        return result.toString();
    }
}