package com.banking.sample.service;

import com.banking.sample.domain.Account;
import com.banking.sample.dto.BalanceDto;
import com.banking.sample.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public BalanceDto deposit(long accountId, double amount){

        Account account= accountRepository.getAccount(accountId);
        if(Objects.isNull(account))
            throw new RuntimeException("Account could not be found");

        int updated=accountRepository.updateBalance(accountId, account.getBalance()+amount);
        if(updated!=1)
            throw new RuntimeException("Error updating...");

        BalanceDto balanceDto=new BalanceDto();
        balanceDto.setBalance(getBalance(accountId));
        return balanceDto;
    }

    public BalanceDto withdraw(long accountId, double amount){

        Account account= accountRepository.getAccount(accountId);
        if(Objects.isNull(account))
            throw new RuntimeException("Account could not be found");

        int updated= accountRepository.updateBalance(accountId, account.getBalance()-amount);
        if(updated!=1)
            throw new RuntimeException("Error updating...");

        BalanceDto balanceDto=new BalanceDto();
        balanceDto.setBalance(getBalance(accountId));
        return balanceDto;
    }

    public double getBalance(long accountId) {
        return accountRepository.getAccount(accountId).getBalance();
    }
}
