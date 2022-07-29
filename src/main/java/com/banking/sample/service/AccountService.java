package com.banking.sample.service;

import com.banking.sample.domain.Account;
import com.banking.sample.exception.ValidationException;
import com.banking.sample.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void createAccount(Account account){
         accountRepository.save(account);
    }

    public Account deposit(long accountId, BigDecimal amount){

        Optional<Account> accountOp= accountRepository.findById(accountId);
        if(accountOp.isEmpty())
            throw new ValidationException("Account could not be found");

        Account account= accountOp.get();
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    public Account withdraw(long accountId, BigDecimal amount){

        Optional<Account> accountOp= accountRepository.findById(accountId);
        if(accountOp.isEmpty())
            throw new ValidationException("Account could not be found");

        Account account= accountOp.get();
        account.setBalance(account.getBalance().subtract(amount));
        return accountRepository.save(account);
    }
}
