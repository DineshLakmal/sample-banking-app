package com.banking.sample.repository;

import com.banking.sample.domain.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

}
