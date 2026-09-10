package com.flatexdegiro.task.service;

import com.flatexdegiro.task.model.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlutParserTest {

    private final FlutParser parser = new FlutParser();

    @Test
    void shouldParseSingleTestCaseWithSinglePile() {
        String input = """
                1
                4 12 3 10 7
                0
                """;

        List<TestCase> result = parser.parseInput(input);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).caseNumber());
        assertEquals(1, result.get(0).piles().size());

        assertArrayEquals(
                new int[]{12, 3, 10, 7},
                result.get(0).piles().get(0).prices()
        );
    }

    @Test
    void shouldParseMultiplePiles() {
        String input = """
                2
                3 7 3 11
                5 1 2 3 4 10
                0
                """;

        List<TestCase> result = parser.parseInput(input);

        assertEquals(1, result.size());

        TestCase testCase = result.get(0);

        assertEquals(2, testCase.piles().size());

        assertArrayEquals(
                new int[]{7, 3, 11},
                testCase.piles().get(0).prices()
        );

        assertArrayEquals(
                new int[]{1, 2, 3, 4, 10},
                testCase.piles().get(1).prices()
        );
    }

    @Test
    void shouldParseMultipleTestCasesAndAssignCaseNumbers() {
        String input = """
                1
                2 5 3
                2
                1 7
                3 10 4 2
                0
                """;

        List<TestCase> result = parser.parseInput(input);

        assertEquals(2, result.size());

        assertEquals(1, result.get(0).caseNumber());
        assertEquals(1, result.get(0).piles().size());

        assertArrayEquals(
                new int[]{5, 3},
                result.get(0).piles().get(0).prices()
        );

        assertEquals(2, result.get(1).caseNumber());
        assertEquals(2, result.get(1).piles().size());

        assertArrayEquals(
                new int[]{7},
                result.get(1).piles().get(0).prices()
        );

        assertArrayEquals(
                new int[]{10, 4, 2},
                result.get(1).piles().get(1).prices()
        );
    }

    @Test
    void shouldStopParsingWhenZeroIsEncountered() {
        String input = """
                1
                2 5 3
                0
                """;

        List<TestCase> result = parser.parseInput(input);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).caseNumber());
    }

    @Test
    void shouldParseOfficialExample() {
        String input = """
                1
                6 12 3 10 7 16 5
                2
                5 7 3 11 9 10
                9 1 2 3 4 10 16 10 4 16
                0
                """;

        List<TestCase> result = parser.parseInput(input);

        assertEquals(2, result.size());

        assertEquals(1, result.get(0).caseNumber());
        assertEquals(1, result.get(0).piles().size());
        assertArrayEquals(
                new int[]{12, 3, 10, 7, 16, 5},
                result.get(0).piles().get(0).prices()
        );

        assertEquals(2, result.get(1).caseNumber());
        assertEquals(2, result.get(1).piles().size());
        assertArrayEquals(
                new int[]{7, 3, 11, 9, 10},
                result.get(1).piles().get(0).prices()
        );
        assertArrayEquals(
                new int[]{1, 2, 3, 4, 10, 16, 10, 4, 16},
                result.get(1).piles().get(1).prices()
        );
    }
}