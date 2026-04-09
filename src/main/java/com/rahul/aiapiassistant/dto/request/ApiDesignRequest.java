package com.rahul.aiapiassistant.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDesignRequest {

    @NotBlank(message = "Resource name is required")
    private String resourceName;

    @NotBlank(message = "Operation is required")
    private String operation;

    @NotBlank(message = "Description is required")
    private String description;

    @Valid
    @NotEmpty(message = "At least one request field is required")
    private List<FieldDefinition> requestFields;

    @Valid
    private List<FieldDefinition> responseFields;

    private List<String> validationRules;
}