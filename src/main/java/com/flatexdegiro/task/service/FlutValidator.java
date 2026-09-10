package com.flatexdegiro.task.service;

import org.springframework.stereotype.Component;

@Component
public class FlutValidator {

    public boolean isValid(String input) {
        if (input == null || input.isBlank()) {
            return false;
        }

        String[] lines = input.strip().split("\\R");
        int lineIndex = 0;
        while (lineIndex < lines.length) {
            String numberOfPilesLine = lines[lineIndex++].trim();
            if (!numberOfPilesLine.matches("\\d+")) {
                return false;
            }

            int numberOfPiles = Integer.parseInt(numberOfPilesLine);
            if (numberOfPiles == 0) {
                return lineIndex == lines.length;
            }

            if (lineIndex + numberOfPiles > lines.length) {
                return false;
            }

            for (int pileIndex = 0; pileIndex < numberOfPiles; pileIndex++) {
                String[] values = lines[lineIndex++].trim().split("\\s+");
                if (values.length == 0 || !values[0].matches("[1-9]\\d*")) {
                    return false;
                }

                int numberOfPrices = Integer.parseInt(values[0]);
                if (values.length != numberOfPrices + 1) {
                    return false;
                }

                for (int i = 1; i < values.length; i++) {
                    if (!values[i].matches("[1-9]\\d*")) {
                        return false;
                    }
                }
            }
        }

        return false;
    }
}