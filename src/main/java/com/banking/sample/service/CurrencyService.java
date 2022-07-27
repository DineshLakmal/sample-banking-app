package com.banking.sample.service;

import com.banking.sample.domain.Currency;
import com.banking.sample.domain.Customer;
import com.banking.sample.dto.CurrencyDto;
import com.banking.sample.dto.CustomerDto;
import com.banking.sample.repository.CurrencyRepository;
import com.banking.sample.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ObjectMapper objectMapper;

    public CurrencyService(CurrencyRepository currencyRepository, ObjectMapper objectMapper){
        this.currencyRepository = currencyRepository;
        this.objectMapper = objectMapper;
    }

    public boolean createCurrency(CurrencyDto currencyDto){
        Currency currency=objectMapper.convertValue(currencyDto, Currency.class);
        int created= currencyRepository.createCurrency(currency);
        return created == 1;
    }
}
