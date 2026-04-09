package com.rahul.aiapiassistant.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDesignResponse {

    private String resourceName;
    private String operation;
    private EndpointSuggestion endpoint;
    private String controllerName;
    private String serviceName;
    private String requestDtoName;
    private String responseDtoName;
    private List<ValidationSuggestion> validations;
    private List<ErrorHandlingSuggestion> errorHandling;
}