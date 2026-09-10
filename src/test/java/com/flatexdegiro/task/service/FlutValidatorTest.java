package com.flatexdegiro.task.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlutValidatorTest {

    private final FlutValidator validator = new FlutValidator();

    @Test
    void shouldAcceptValidInput() {
        String input = """
                1
                6 12 3 10 7 16 5
                0
                """;

        assertTrue(validator.isValid(input));
    }

    @Test
    void shouldAcceptMultipleTestCases() {
        String input = """
                1
                2 5 3
                2
                3 7 3 11
                1 5
                0
                """;

        assertTrue(validator.isValid(input));
    }

    @Test
    void shouldAcceptOnlyZero() {
        assertTrue(validator.isValid("0"));
    }

    @Test
    void shouldRejectNullOrBlankInput() {
        assertFalse(validator.isValid(null));
        assertFalse(validator.isValid(""));
        assertFalse(validator.isValid("   "));
    }

    @Test
    void shouldRejectIncorrectNumberOfPrices() {
        String input = """
                1
                1 2 3
                0
                """;

        assertFalse(validator.isValid(input));
    }

    @Test
    void shouldRejectIncorrectNumberOfPiles() {
        String input = """
                2
                2 1 1
                0
                """;

        assertFalse(validator.isValid(input));
    }

    @Test
    void shouldRejectInvalidValues() {
        assertFalse(validator.isValid("""
                1
                2 -1 5
                0
                """));

        assertFalse(validator.isValid("""
                1
                2 1 abc
                0
                """));

        assertFalse(validator.isValid("""
                1
                2 1 0
                0
                """));
    }

    @Test
    void shouldRejectContentAfterTerminatingZero() {
        String input = """
                0
                1
                1 5
                """;

        assertFalse(validator.isValid(input));
    }
}