package com.banking.sample.repository;

import com.banking.sample.domain.Account;
import com.banking.sample.repository.mappers.AccountRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccountRepository {

    private  final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Account getAccount(long accountId){
        Map<String, Object> params=new HashMap<>();
        params.put("ACCOUNT_ID", accountId);

        String sql="select * from account where account_id=:ACCOUNT_ID";
        return namedParameterJdbcTemplate.queryForObject(sql, params, new AccountRowMapper());
    }

    public int updateBalance(long accountId, double balance){
        Map<String, Object> params=new HashMap<>();
        params.put("ACCOUNT_ID", accountId);
        params.put("BALANCE", balance);

        String sql="update account set balance=:BALANCE where account_id=:ACCOUNT_ID";
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
