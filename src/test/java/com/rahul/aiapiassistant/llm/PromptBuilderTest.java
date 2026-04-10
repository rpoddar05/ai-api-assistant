package com.rahul.aiapiassistant.llm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.aiapiassistant.dto.request.ApiDesignRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PromptBuilderTest {

    private final PromptBuilder promptBuilder =
            new PromptBuilder(new ObjectMapper());

    @Test
    void shouldContainStrictRulesAndSchema() {
        ApiDesignRequest request = new ApiDesignRequest();
        request.setResourceName("User");
        request.setOperation("CREATE");

        String prompt = promptBuilder.buildApiDesignPrompt(request);

        assertTrue(prompt.contains("STRICT RULES"));
        assertTrue(prompt.contains("REQUIRED RESPONSE SCHEMA"));
        assertTrue(prompt.contains("resourceName"));
    }
}
