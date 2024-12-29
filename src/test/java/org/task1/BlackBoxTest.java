package org.task1;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class BlackBoxTest {

    @DisplayName("Caso semplice con tutti i valori corretti")
    @Test
    public void correctCaseTest() {
        // Input
        String original = "hello world", target = "world", replacement = "everyone";
        // Expected output
        String expected = "hello everyone";
        Assertions.assertEquals(expected, StringUtils.replace(original, target, replacement));
    }

    @DisplayName("Eccezione NullPointerException su original null")
    @Test
    public void nullOriginalTest() {
        // Input
        String original = null, target = "world", replacement = "everyone";
        Assertions.assertThrows(NullPointerException.class, () -> StringUtils.replace(original, target, replacement));
    }

    @DisplayName("Eccezione IllegalArgumentException su target vuoto")
    @Test
    public void emptyTargetTest() {
        // Input
        String original = "hello world", target = "", replacement = "everyone";
        Assertions.assertThrows(IllegalArgumentException.class, () -> StringUtils.replace(original, target, replacement));
    }

    @DisplayName("Nessuna sostituzione effettuata quando target non esiste")
    @Test
    public void noReplacementNeededTest() {
        // Input
        String original = "hello world", target = "planet", replacement = "everyone";

        // Expected output
        String expected = "hello world";
        Assertions.assertEquals(expected, StringUtils.replace(original, target, replacement));
    }
}