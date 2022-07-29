package com.banking.sample.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class Customer {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name="customer_id")
   private long customerId;

   @Column(name="first_name")
   private String firstName;

   @Column(name="last_name")
   private String lastName;
}
