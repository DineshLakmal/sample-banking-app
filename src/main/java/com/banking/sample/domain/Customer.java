package com.banking.sample.domain;

import lombok.Data;

@Data
public class Customer {

   private long customerId;
   private String firstName;
   private String lastName;
   private String accountType;
   private double balance;
   private long currencyId;
}
