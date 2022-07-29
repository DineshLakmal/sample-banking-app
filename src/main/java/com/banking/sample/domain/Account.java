package com.banking.sample.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="account_id")
    private long accountId;

    @Column(name="customer_id")
    private long customerId;

    @Column(name="currency_id")
    private long currencyId;

    @Column(name="balance")
    private BigDecimal balance;
}
