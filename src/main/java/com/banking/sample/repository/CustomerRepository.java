package com.banking.sample.repository;

import com.banking.sample.domain.Customer;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    private  final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CustomerRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int createCustomer(Customer customer){
        MapSqlParameterSource customerParams = new MapSqlParameterSource()
                .addValue( "F_NAME", customer.getFirstName())
                .addValue( "L_NAME",  customer.getLastName());

        String sql="insert into customer(f_name, l_name) values(:F_NAME, :L_NAME)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int cusCreated= namedParameterJdbcTemplate.update(sql, customerParams, keyHolder, new String[]{"customer_id"});

        MapSqlParameterSource accountParams = new MapSqlParameterSource()
                .addValue( "CUSTOMER_ID", keyHolder.getKey())
                .addValue( "CURRENCY_ID",  customer.getCurrencyId())
                .addValue( "ACCOUNT_TYPE",  customer.getAccountType())
                .addValue( "BALANCE",  customer.getBalance());

        sql="insert into account(customer_id, currency_id, account_type, balance) values(:CUSTOMER_ID, :CURRENCY_ID, :ACCOUNT_TYPE, :BALANCE)";
        int accCreated=namedParameterJdbcTemplate.update(sql, accountParams);

        return cusCreated;
    }
}
