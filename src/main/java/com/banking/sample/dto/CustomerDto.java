package com.banking.sample.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String accountType;
    private double balance;
    private long currencyId;
}
