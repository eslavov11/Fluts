package com.flatexdegiro.task.service;

import com.flatexdegiro.task.model.TradingResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlutFormatter {

    private static final int MAXIMUM_VALUES = 10;

    public String format(TradingResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("schuurs %d%n", result.caseNumber()));
        sb.append(String.format("Maximum profit is %d.%n", result.maxProfit()));
        sb.append("Number of fluts to buy: ");

        List<Integer> counts = result.sortedCounts();
        int limit = Math.min(MAXIMUM_VALUES, counts.size());
        for (int i = 0; i < limit; i++) {
            sb.append(counts.get(i));
            if (i < limit - 1)  {
                sb.append(" ");
            }
        }

        return sb.append(System.lineSeparator())
                .toString();
    }
}