package com.banking.sample.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="currency_id")
    private long currencyId;

    @Column(name="name")
    private String name;
}
