package com.flatexdegiro.task.service;

import com.flatexdegiro.task.model.Pile;
import com.flatexdegiro.task.model.TestCase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlutParser {

    public List<TestCase> parseInput(String inputData) {
        List<TestCase> cases = new ArrayList<>();
        String[] lines = inputData.strip().split("\\R");
        int lineIndex = 0;
        int testCaseNumber = 1;
        while (lineIndex < lines.length) {
            int numberOfPiles = Integer.parseInt(lines[lineIndex++].trim());
            if (numberOfPiles == 0) {
                break;
            }

            List<Pile> piles = new ArrayList<>();
            for (int i = 0; i < numberOfPiles; i++) {
                String[] values = lines[lineIndex++].trim().split("\\s+");
                int numberOfPrices = Integer.parseInt(values[0]);
                int[] prices = new int[numberOfPrices];
                for (int j = 0; j < numberOfPrices; j++) {
                    prices[j] = Integer.parseInt(values[j + 1]);
                }

                piles.add(new Pile(prices));
            }

            cases.add(new TestCase(testCaseNumber++, piles));
        }

        return cases;
    }
}