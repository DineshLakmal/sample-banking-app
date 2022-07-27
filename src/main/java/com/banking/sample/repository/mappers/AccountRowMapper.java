package com.banking.sample.repository.mappers;

import com.banking.sample.domain.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account=new Account();
        account.setAccountId(rs.getLong("account_id"));
        account.setCurrencyId(rs.getLong("customer_id"));
        account.setCurrencyId(rs.getLong("currency_id"));
        account.setAccountType(rs.getString("account_type"));
        account.setBalance(rs.getDouble("balance"));

        return account;
    }
}
