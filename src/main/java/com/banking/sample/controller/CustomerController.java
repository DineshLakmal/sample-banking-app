package com.banking.sample.controller;

import com.banking.sample.constants.AppConstants;
import com.banking.sample.domain.Account;
import com.banking.sample.domain.Customer;
import com.banking.sample.dto.CustomerRegistrationDto;
import com.banking.sample.dto.ResponseDto;
import com.banking.sample.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ObjectMapper objectMapper;

    public CustomerController(CustomerService customerService, ObjectMapper objectMapper){
        this.customerService = customerService;
        this.objectMapper = objectMapper;
    }

    /**
     * This endpoint creates customer
     * @param customerRegistrationDto CustomerDto
     * @return ResponseEntity
     */
   @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ResponseDto> create(@RequestBody CustomerRegistrationDto customerRegistrationDto){

       Customer customer=objectMapper.convertValue(customerRegistrationDto, Customer.class);
       Account account=objectMapper.convertValue(customerRegistrationDto, Account.class);
       Customer customerCreated=customerService.createCustomer(customer, account);

       ResponseDto responseDto=new ResponseDto();
       responseDto.setMessage(AppConstants.SUCCESS_MSG);
       responseDto.setResponseBody(customerCreated);

       return  ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
   }

}
