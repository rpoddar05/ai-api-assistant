package com.rahul.aiapiassistant.llm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.aiapiassistant.dto.request.ApiDesignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromptBuilder {

    private final ObjectMapper objectMapper;

    public String buildApiDesignPrompt(ApiDesignRequest request) {
        try {
            String requestJson = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(request);

            return """
                    You are a senior backend API design assistant.

                    Return ONLY valid JSON.
                    Do not include markdown.
                    Do not include code fences.
                    Do not include explanation before or after the JSON.
                    Every field must exactly match the required schema.
                    Do not add extra fields.
                    Do not rename fields.
                    If no validations or error handling items exist, return empty arrays.

                    Input requirements:
                    %s

                    Return JSON in exactly this schema:

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

        } catch (Exception e) {
            throw new RuntimeException("Failed to build prompt", e);
        }
    }
}