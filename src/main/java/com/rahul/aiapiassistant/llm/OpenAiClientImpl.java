package com.rahul.aiapiassistant.llm;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.rahul.aiapiassistant.config.OpenAiProperties;
import com.rahul.aiapiassistant.exception.LlmIntegrationException;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenAiClientImpl implements LlmClient {

    private final OpenAiProperties openAiProperties;
    private OpenAIClient sdkClient;

    private OpenAIClient getSdkClient() {
        if (sdkClient == null) {
            sdkClient = OpenAIOkHttpClient.builder()
                    .apiKey(openAiProperties.getApiKey())
                    .build();
        }
        return sdkClient;
    }

    @Override
    public String generateApiDesignJson(String prompt) {
        try {
            ResponseCreateParams params = ResponseCreateParams.builder()
                    .input(prompt)
                    .model(openAiProperties.getModel())
                    .build();

            Response response = getSdkClient().responses().create(params);
           

            String outputText = extractOutputText(response);
        

            if (outputText == null || outputText.isBlank()) {
                throw new LlmIntegrationException("OpenAI returned an empty response", null);
            }

            return outputText;

        } catch (LlmIntegrationException e) {
            throw e;
        } catch (Exception e) {
            throw new LlmIntegrationException("Failed to call OpenAI API", e);
        }
    }

    private String extractOutputText(Response response) {
        for (var item : response.output()) {
            if (item.message().isPresent()) {
                var message = item.message().get();

                for (var content : message.content()) {
                    if (content.outputText().isPresent()) {
                        return content.outputText().get().text();
                    }
                }
            }
        }

        throw new LlmIntegrationException("No text output found", null);
    }

    @PreDestroy
    public void closeClient() {
        if (sdkClient != null) {
            sdkClient.close();
        }
    }
}