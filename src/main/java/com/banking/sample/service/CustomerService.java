package com.banking.sample.service;

import com.banking.sample.domain.Customer;
import com.banking.sample.dto.CustomerDto;
import com.banking.sample.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper){
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    public boolean createCustomer(CustomerDto customerDto){
        Customer customer=objectMapper.convertValue(customerDto, Customer.class);
        int created= customerRepository.createCustomer(customer);
        return created == 1;
    }

}
