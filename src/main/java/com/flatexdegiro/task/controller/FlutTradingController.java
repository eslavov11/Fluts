package com.flatexdegiro.task.controller;

import com.flatexdegiro.task.service.FlutTradingService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fluts")
public class FlutTradingController {

    private final FlutTradingService flutTradingService;

    public FlutTradingController(FlutTradingService flutTradingService) {
        this.flutTradingService = flutTradingService;
    }

    @PostMapping(value = "/process",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> processTradingData(@RequestBody String inputData) {

        String result = flutTradingService.processTestCases(inputData);

        return ResponseEntity.ok(result);
    }
}