package com.rahul.aiapiassistant.llm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.aiapiassistant.dto.response.ApiDesignResponse;
import com.rahul.aiapiassistant.exception.InvalidLlmResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LlmResponseParser {

    private final ObjectMapper objectMapper;

    public ApiDesignResponse parseApiDesignResponse(String rawJson) {
        try {
            return objectMapper.readValue(rawJson, ApiDesignResponse.class);
        } catch (Exception e) {
            throw new InvalidLlmResponseException("Failed to parse LLM response", e);
        }
    }
}