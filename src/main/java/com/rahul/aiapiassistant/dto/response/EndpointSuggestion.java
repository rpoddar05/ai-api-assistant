package com.rahul.aiapiassistant.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndpointSuggestion {

    private String httpMethod;
    private String path;
    private String purpose;
}