package com.flatexdegiro.task.service;

import com.flatexdegiro.task.model.TestCase;
import com.flatexdegiro.task.model.TradingResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlutTradingService {

    private final FlutValidator validator;
    private final FlutParser parser;
    private final FlutCalculator calculator;
    private final FlutFormatter formatter;

    public FlutTradingService(FlutValidator validator,
                              FlutParser parser,
                              FlutCalculator calculator,
                              FlutFormatter formatter) {
        this.validator = validator;
        this.parser = parser;
        this.calculator = calculator;
        this.formatter = formatter;
    }

    public String processTestCases(String rawInput) {
        if (!validator.isValid(rawInput)) {
            throw new IllegalArgumentException("Invalid input!");
        }

        List<TestCase> testCases = parser.parseInput(rawInput);
        StringBuilder fullResponse = new StringBuilder();
        for (TestCase testCase : testCases) {
            TradingResult result = calculator.calculate(testCase);
            fullResponse.append(formatter.format(result));
        }

        return fullResponse.toString();
    }
}