package com.banking.sample.domain;

import lombok.Data;

@Data
public class Account {

    private long accountId;
    private long customerId;
    private long currencyId;
    private String accountType;
    private double balance;
}
