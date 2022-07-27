package com.banking.sample.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String message;
    private Object responseBody;
}
