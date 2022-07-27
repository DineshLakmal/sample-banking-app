package com.banking.sample.controller;

import com.banking.sample.constants.AppConstants;
import com.banking.sample.domain.Customer;
import com.banking.sample.dto.CustomerDto;
import com.banking.sample.dto.ResponseDto;
import com.banking.sample.service.CustomerService;
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

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

   @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> create(@RequestBody CustomerDto customerDto){

       boolean customerCreated=customerService.createCustomer(customerDto);
       String msg;
       if(customerCreated){
           msg=AppConstants.SUCCESS_MSG;
       }else{
           msg=AppConstants.ERROR_MSG;
       }

       ResponseDto responseDto=new ResponseDto();
       responseDto.setMessage(msg);
       responseDto.setResponseBody(customerCreated);

       return  ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
   }

}
