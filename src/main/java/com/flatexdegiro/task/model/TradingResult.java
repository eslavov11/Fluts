package com.flatexdegiro.task.model;

import java.util.List;

public record TradingResult(int caseNumber, long maxProfit, List<Integer> sortedCounts) {}
