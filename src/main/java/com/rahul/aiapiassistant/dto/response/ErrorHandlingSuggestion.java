package com.rahul.aiapiassistant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorHandlingSuggestion {

    private String scenario;
    private String exceptionType;
    private String recommendedResponse;
}
