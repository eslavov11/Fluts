package com.flatexdegiro.task.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlutTradingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldProcessExampleInput() throws Exception {
        String input = """
                1
                6 12 3 10 7 16 5
                2
                5 7 3 11 9 10
                9 1 2 3 4 10 16 10 4 16
                0
                """;

        String expectedOutput = """
                schuurs 1
                Maximum profit is 8.
                Number of fluts to buy: 4
                schuurs 2
                Maximum profit is 40.
                Number of fluts to buy: 6 7 8 9 10 12 13
                """;

        mockMvc.perform(post("/api/v1/fluts/process")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(input))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(expectedOutput));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            """
            1
            1 2 3
            0
            """,
            """
            1
            2 1
            0
            """,
            """
            2
            2 1 1
            0
            """,
            """
            1
            2 1 1
            2 1 1
            0
            """
    })
    void shouldReturnBadRequestForInvalidInputStructure(String input) throws Exception {
        mockMvc.perform(post("/api/v1/fluts/process")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(input))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            """
            1
            2 1 abc
            0
            """,
            """
            1
            2 -1 5
            0
            """,
            """
            -1
            0
            """,
            """
            1
            2 1 0
            0
            """
    })
    void shouldReturnBadRequestForInvalidValues(String input) throws Exception {
        mockMvc.perform(post("/api/v1/fluts/process")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(input))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenInputDoesNotEndWithZero() throws Exception {
        String input = """
                1
                2 1 5
                """;

        mockMvc.perform(post("/api/v1/fluts/process")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(input))
                .andExpect(status().isBadRequest());
    }
}