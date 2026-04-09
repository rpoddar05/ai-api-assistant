package com.rahul.aiapiassistant.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDefinition {

    @NotBlank(message = "Field name is required")
    private String name;

    @NotBlank(message = "Field type is required")
    private String type;

    private boolean required;

    private String description;
}
