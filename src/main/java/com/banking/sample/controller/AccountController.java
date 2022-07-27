package com.banking.sample.controller;

import com.banking.sample.dto.AmountDto;
import com.banking.sample.dto.BalanceDto;
import com.banking.sample.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * This endpoints deposit money into a given account
     * @param accountId Long
     * @param amountDto AmountDto
     * @return ResponseEntity
     */
    @PostMapping(value = "{accountId}/deposits", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deposit(@PathVariable Long accountId, @RequestBody AmountDto amountDto){

        BalanceDto balanceDto=accountService.deposit(accountId, amountDto.getAmount());
        return  ResponseEntity.status(HttpStatus.OK).body(balanceDto);
    }

    /**
     * This endpoint withdraws money from a given account
     *
     * @param accountId Long
     * @param amountDto AmountDto
     * @return ResponseEntity
     */
    @PostMapping(value = "{accountId}/withdraws", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> withdraw(@PathVariable Long accountId, @RequestBody AmountDto amountDto){

        BalanceDto balanceDto=accountService.withdraw(accountId, amountDto.getAmount());
        return  ResponseEntity.status(HttpStatus.OK).body(balanceDto);
    }
}
