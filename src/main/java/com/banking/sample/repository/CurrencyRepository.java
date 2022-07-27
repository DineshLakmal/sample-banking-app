package com.banking.sample.repository;

import com.banking.sample.domain.Currency;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrencyRepository {

    private  final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CurrencyRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int createCurrency(Currency currency){
        Map<String, Object> params=new HashMap<>();
        params.put("NAME", currency.getName());

        String sql="insert into currency(name) values(:NAME)";
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
