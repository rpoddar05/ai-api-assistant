package com.rahul.aiapiassistant.service;

import com.rahul.aiapiassistant.dto.request.ApiDesignRequest;
import com.rahul.aiapiassistant.dto.response.ApiDesignResponse;

import com.rahul.aiapiassistant.llm.LlmClient;
import com.rahul.aiapiassistant.llm.LlmResponseParser;
import com.rahul.aiapiassistant.llm.PromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiAssistantServiceImpl implements ApiAssistantService {

    private final PromptBuilder promptBuilder;
    private final LlmClient llmClient;
    private final LlmResponseParser llmResponseParser;


    @Override
    public ApiDesignResponse generateApiDesign(ApiDesignRequest request) {
        String prompt = promptBuilder.buildApiDesignPrompt(request);

        String rawResponse = llmClient.generateApiDesignJson(prompt);

        System.out.println("RAW LLM RESPONSE:");
        System.out.println(rawResponse);


        return llmResponseParser.parseApiDesignResponse(rawResponse);

    }
}
