package com.banking.sample.service;

import com.banking.sample.domain.Currency;
import com.banking.sample.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository){
        this.currencyRepository = currencyRepository;
    }

    public Currency createCurrency(Currency currency){
        return currencyRepository.save(currency);
    }
}
