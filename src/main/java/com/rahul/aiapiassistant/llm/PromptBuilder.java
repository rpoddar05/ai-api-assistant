package com.rahul.aiapiassistant.llm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.aiapiassistant.dto.request.ApiDesignRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PromptBuilder {

    private final ObjectMapper objectMapper;

    public String buildApiDesignPrompt(ApiDesignRequest request) {
        try {
            String requestJson = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(request);

            String prompt = """
                    You are a senior backend API design assistant.

                    Your task is to analyze API requirements and return ONLY valid JSON.

                    STRICT RULES:
                    1. Return only JSON.
                    2. Do not include markdown.
                    3. Do not include code fences.
                    4. Do not include commentary or explanation.
                    5. Do not add extra fields.
                    6. Do not rename fields.
                    7. Every field in the required schema must be present.
                    8. If a list has no items, return an empty array.
                    9. Keep class names and endpoint naming aligned with the requested resource and operation.

                    INPUT REQUIREMENTS:
                    %s

                    REQUIRED RESPONSE SCHEMA:
                    {
                      "resourceName": "string",
                      "operation": "string",
                      "endpoint": {
                        "httpMethod": "string",
                        "path": "string",
                        "purpose": "string"
                      },
                      "controllerName": "string",
                      "serviceName": "string",
                      "requestDtoName": "string",
                      "responseDtoName": "string",
                      "validations": [
                        {
                          "field": "string",
                          "rule": "string",
                          "reason": "string"
                        }
                      ],
                      "errorHandling": [
                        {
                          "scenario": "string",
                          "exceptionType": "string",
                          "recommendedResponse": "string"
                        }
                      ]
                    }
                    """.formatted(requestJson);

            log.debug("Prompt built successfully");
            return prompt;

        } catch (Exception e) {
            log.error("Failed to build prompt", e);
            throw new RuntimeException("Failed to build prompt", e);
        }
    }
}