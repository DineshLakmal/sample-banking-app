package com.banking.sample.service;

import com.banking.sample.domain.Account;
import com.banking.sample.exception.ValidationException;
import com.banking.sample.repository.AccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    private static final Logger LOGGER = LogManager.getLogger(AccountServiceTest.class);

    @Test
    void depositTest(){
        long accountId=2;//this id already existing in the DB
        BigDecimal depositAmount=new BigDecimal(500);

        Optional<Account> account= accountRepository.findById(accountId);
        BigDecimal currentBalance;
        if (account.isPresent()) {
            currentBalance = account.get().getBalance();
        }else{
            throw new ValidationException("Account not found");
        }


        Account updatedAccount=accountService.deposit(accountId, depositAmount);
        Assertions.assertNotNull(updatedAccount);
        Assertions.assertEquals(
                   currentBalance.add(depositAmount).toString(),
                   updatedAccount.getBalance().toString());

    }

    @Test
    void withdrawTest(){
        long accountId=2;//this id already existing in the DB
        BigDecimal withdrawAmount=new BigDecimal(500);

        Optional<Account> account= accountRepository.findById(accountId);
        BigDecimal currentBalance;
        if (account.isPresent()) {
            currentBalance = account.get().getBalance();
        }else{
            throw new ValidationException("Account not found");
        }

        Account updatedAccount=accountService.withdraw(accountId, withdrawAmount);
        Assertions.assertNotNull(updatedAccount);
        Assertions.assertEquals(
                currentBalance.subtract(withdrawAmount).toString(),
                updatedAccount.getBalance().toString());

    }
}
