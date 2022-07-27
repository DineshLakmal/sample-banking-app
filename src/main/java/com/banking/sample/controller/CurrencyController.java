package com.banking.sample.controller;

import com.banking.sample.constants.AppConstants;
import com.banking.sample.dto.CurrencyDto;
import com.banking.sample.dto.CustomerDto;
import com.banking.sample.dto.ResponseDto;
import com.banking.sample.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService){
        this.currencyService=currencyService;
    }

    /**
     * This end point creates currencies
     * @param currencyDto CurrencyDto
     * @return ResponseEntity
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody CurrencyDto currencyDto){

        boolean currencyCreated=currencyService.createCurrency(currencyDto);
        String msg;
        if(currencyCreated){
            msg= AppConstants.SUCCESS_MSG;
        }else{
            msg=AppConstants.ERROR_MSG;
        }

        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage(msg);
        responseDto.setResponseBody(currencyCreated);

        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
