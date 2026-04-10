package com.rahul.aiapiassistant.llm;

import com.rahul.aiapiassistant.dto.response.ApiDesignResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LlmResponseParserTest {

    private final LlmResponseParser parser =
            new LlmResponseParser(new com.fasterxml.jackson.databind.ObjectMapper());

    @Test
    void shouldParseValidJsonResponse() {
        String json = """
                {
                  "resourceName": "User",
                  "operation": "CREATE",
                  "endpoint": {
                    "httpMethod": "POST",
                    "path": "/users",
                    "purpose": "Create user"
                  },
                  "controllerName": "UserController",
                  "serviceName": "UserService",
                  "requestDtoName": "CreateUserRequest",
                  "responseDtoName": "CreateUserResponse",
                  "validations": [],
                  "errorHandling": []
                }
                """;

        ApiDesignResponse response = parser.parseApiDesignResponse(json);

        assertEquals("User", response.getResourceName());
        assertEquals("CREATE", response.getOperation());
        assertEquals("UserController", response.getControllerName());
    }

    @Test
    void shouldCleanMarkdownAndParse() {
        String json = """
            ```json
            {
              "resourceName": "User",
              "operation": "CREATE",
              "endpoint": {
                "httpMethod": "POST",
                "path": "/users",
                "purpose": "Create user"
              },
              "controllerName": "UserController",
              "serviceName": "UserService",
              "requestDtoName": "CreateUserRequest",
              "responseDtoName": "CreateUserResponse",
              "validations": [],
              "errorHandling": []
            }
            ```
            """;

        ApiDesignResponse response = parser.parseApiDesignResponse(json);

        assertNotNull(response);
        assertEquals("User", response.getResourceName());
    }

    @Test
    void shouldThrowExceptionForInvalidJson() {
        String invalidJson = "{ invalid json }";

        assertThrows(
                RuntimeException.class,
                () -> parser.parseApiDesignResponse(invalidJson)
        );
    }
}
