package com.rahul.aiapiassistant.llm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.aiapiassistant.dto.response.ApiDesignResponse;
import com.rahul.aiapiassistant.exception.InvalidLlmResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LlmResponseParser {

    private final ObjectMapper objectMapper;

    public ApiDesignResponse parseApiDesignResponse(String rawJson) {
        try {
            String cleanedJson = cleanJson(rawJson);
            ApiDesignResponse response = objectMapper.readValue(cleanedJson, ApiDesignResponse.class);
            log.info("Successfully parsed LLM response into ApiDesignResponse");
            return response;
        } catch (Exception e) {
            log.error("Failed to parse LLM response. rawJson={}", rawJson, e);
            throw new InvalidLlmResponseException("Failed to parse LLM response into ApiDesignResponse", e);
        }
    }

    private String cleanJson(String rawJson) {
        return rawJson
                .replace("```json", "")
                .replace("```", "")
                .trim();
    }
}