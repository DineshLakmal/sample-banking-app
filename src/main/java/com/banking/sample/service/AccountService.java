package com.banking.sample.service;

import com.banking.sample.domain.Account;
import com.banking.sample.enums.OperationType;
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
        return updateAccountBalance(accountId, amount, OperationType.DEPOSIT);
    }

    public Account withdraw(long accountId, BigDecimal amount){
        return updateAccountBalance(accountId, amount, OperationType.WITHDRAW);
    }

    private synchronized Account updateAccountBalance(long accountId, BigDecimal amount, OperationType operationType){

        Optional<Account> accountOp= accountRepository.findById(accountId);
        if(accountOp.isEmpty())
            throw new ValidationException("Account could not be found");

        Account account= accountOp.get();
        if(operationType.equals(OperationType.DEPOSIT)){

            account.setBalance(account.getBalance().add(amount));
        }else if(operationType.equals(OperationType.WITHDRAW)){

            BigDecimal newBalance=account.getBalance().subtract(amount);
            BigDecimal minBalance=new BigDecimal("0.0");
            if(newBalance.compareTo(minBalance)>=0){
                account.setBalance(newBalance);
            }else {
                throw new ValidationException("Account balance is insufficient");
            }

        }else{
            throw new ValidationException("Invalid operation type");
        }

        return accountRepository.save(account);
    }
}
