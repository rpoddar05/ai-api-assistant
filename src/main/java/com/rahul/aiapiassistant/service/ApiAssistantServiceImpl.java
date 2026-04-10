package com.rahul.aiapiassistant.service;

import com.rahul.aiapiassistant.dto.request.ApiDesignRequest;
import com.rahul.aiapiassistant.dto.response.ApiDesignResponse;

import com.rahul.aiapiassistant.llm.LlmClient;
import com.rahul.aiapiassistant.llm.LlmResponseParser;
import com.rahul.aiapiassistant.llm.PromptBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiAssistantServiceImpl implements ApiAssistantService {

    private final PromptBuilder promptBuilder;
    private final LlmClient llmClient;
    private final LlmResponseParser llmResponseParser;


    @Override
    public ApiDesignResponse generateApiDesign(ApiDesignRequest request) {

        log.info("Generating API design. resourceName={}, operation={}",
                request.getResourceName(), request.getOperation());


        String prompt = promptBuilder.buildApiDesignPrompt(request);
        log.debug("Prompt built successfully for resourceName={}", request.getResourceName());

        String rawResponse = llmClient.generateApiDesignJson(prompt);
        log.debug("Raw LLM response received for resourceName={}", request.getResourceName());


        ApiDesignResponse parsedResponse = llmResponseParser.parseApiDesignResponse(rawResponse);
        log.info("API design generated successfully. controllerName={}, serviceName={}",
                parsedResponse.getControllerName(), parsedResponse.getServiceName());

        return parsedResponse;

    }
}
