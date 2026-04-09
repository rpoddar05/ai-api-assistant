package com.rahul.aiapiassistant.controller;

import com.rahul.aiapiassistant.dto.request.ApiDesignRequest;
import com.rahul.aiapiassistant.dto.response.ApiDesignResponse;
import com.rahul.aiapiassistant.service.ApiAssistantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/api-assistant")
public class ApiAssistantController {

    private final ApiAssistantService apiAssistantService;

    public ApiAssistantController(ApiAssistantService apiAssistantService) {
        this.apiAssistantService = apiAssistantService;
    }

    @PostMapping("/design")
    public ResponseEntity<ApiDesignResponse> generateApiDesign(
            @Valid @RequestBody ApiDesignRequest request) {

        ApiDesignResponse response = apiAssistantService.generateApiDesign(request);

        return ResponseEntity.ok(response);
    }
}
