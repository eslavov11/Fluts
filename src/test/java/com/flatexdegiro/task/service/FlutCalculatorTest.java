package com.flatexdegiro.task.service;

import com.flatexdegiro.task.model.Pile;
import com.flatexdegiro.task.model.TestCase;
import com.flatexdegiro.task.model.TradingResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlutCalculatorTest {

    private final FlutCalculator calculator = new FlutCalculator();

    @Test
    void shouldCalculateMaximumProfitForSinglePile() {
        TestCase testCase = new TestCase(
                1,
                List.of(new Pile(new int[]{12, 3, 10, 7, 16, 5}))
        );

        TradingResult result = calculator.calculate(testCase);

        assertEquals(8, result.maxProfit());
        assertEquals(List.of(4), result.sortedCounts());
    }

    @Test
    void shouldReturnAllPossibleCountsWhenProfitIsTheSame() {
        TestCase testCase = new TestCase(
                1,
                List.of(new Pile(new int[]{5, 15}))
        );

        TradingResult result = calculator.calculate(testCase);

        assertEquals(5, result.maxProfit());
        assertEquals(List.of(1), result.sortedCounts());
    }

    @Test
    void shouldCombinePossibleCountsFromMultiplePiles() {
        TestCase testCase = new TestCase(
                1,
                List.of(
                        new Pile(new int[]{5, 15, 5}),
                        new Pile(new int[]{5})
                )
        );

        TradingResult result = calculator.calculate(testCase);

        assertEquals(10, result.maxProfit());
        assertEquals(List.of(2, 4), result.sortedCounts());
    }
}