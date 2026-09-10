package com.flatexdegiro.task.service;

import com.flatexdegiro.task.model.Pile;
import com.flatexdegiro.task.model.TestCase;
import com.flatexdegiro.task.model.TradingResult;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FlutCalculator {
    
    private static final int PRICE_OF_FLUT_IN_HOLLAND = 10;

    public TradingResult calculate(TestCase testCase) {
        long totalMaxProfit = 0;
        Set<Integer> overallPossibleCounts = new HashSet<>();
        overallPossibleCounts.add(0);

        for (Pile pile : testCase.piles()) {
            long maxPileProfit = 0;
            long currentProfit = 0;
            List<Integer> bestCountsForPile = new ArrayList<>();
            bestCountsForPile.add(0);

            int[] prices = pile.prices();
            for (int i = 0; i < prices.length; i++) {
                currentProfit += (PRICE_OF_FLUT_IN_HOLLAND - prices[i]);

                if (currentProfit > maxPileProfit) {
                    maxPileProfit = currentProfit;
                    bestCountsForPile.clear();
                    bestCountsForPile.add(i + 1);
                } else if (currentProfit == maxPileProfit) {
                    bestCountsForPile.add(i + 1);
                }
            }

            totalMaxProfit += maxPileProfit;

            Set<Integer> newOverallCounts = new HashSet<>();
            for (int prevCount : overallPossibleCounts) {
                for (int currentCount : bestCountsForPile) {
                    newOverallCounts.add(prevCount + currentCount);
                }
            }
            overallPossibleCounts = newOverallCounts;
        }

        List<Integer> sortedCounts = new ArrayList<>(overallPossibleCounts);
        Collections.sort(sortedCounts);

        return new TradingResult(testCase.caseNumber(), totalMaxProfit, sortedCounts);
    }
}
