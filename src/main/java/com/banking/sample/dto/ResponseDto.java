package com.banking.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseDto {
    private String message;

    @JsonProperty("data")
    private Object responseBody;
}
