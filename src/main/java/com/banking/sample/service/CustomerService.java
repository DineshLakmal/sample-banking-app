package com.banking.sample.service;

import com.banking.sample.domain.Account;
import com.banking.sample.domain.Customer;
import com.banking.sample.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountService accountService;

    public CustomerService(CustomerRepository customerRepository, AccountService accountService){
        this.customerRepository = customerRepository;
        this.accountService = accountService;
    }

    public Customer createCustomer(Customer customer, Account account){
        Customer createdCustomer= customerRepository.save(customer);

        account.setCustomerId(createdCustomer.getCustomerId());
        accountService.createAccount(account);
        return createdCustomer;
    }

}
