package com.banking.sample.controller;

import com.banking.sample.constants.AppConstants;
import com.banking.sample.domain.Currency;
import com.banking.sample.dto.CurrencyDto;
import com.banking.sample.dto.ResponseDto;
import com.banking.sample.service.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final ObjectMapper objectMapper;

    public CurrencyController(CurrencyService currencyService, ObjectMapper objectMapper){
        this.currencyService=currencyService;
        this.objectMapper = objectMapper;
    }

    /**
     * This endpoint creates currencies
     * @param currencyDto CurrencyDto
     * @return ResponseEntity
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> create(@RequestBody CurrencyDto currencyDto){
        Currency currency=objectMapper.convertValue(currencyDto, Currency.class);
        Currency currencyCreated=currencyService.createCurrency(currency);

        String msg;
        if(Objects.isNull(currencyCreated)){
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
