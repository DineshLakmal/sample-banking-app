package com.banking.sample.dto;

import lombok.Data;

@Data
public class CustomerRegistrationDto {

    private String firstName;
    private String lastName;
    private double balance;
    private long currencyId;
}
