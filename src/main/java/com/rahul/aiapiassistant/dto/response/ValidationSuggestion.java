package com.rahul.aiapiassistant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationSuggestion {

    private String field;
    private String rule;
    private String reason;
}
