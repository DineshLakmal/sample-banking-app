package com.banking.sample.controller;

import com.banking.sample.constants.AppConstants;
import com.banking.sample.domain.Account;
import com.banking.sample.dto.AmountDto;
import com.banking.sample.dto.ResponseDto;
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
     * This endpoint deposits money into a given account
     * @param accountId Long
     * @param amountDto AmountDto
     * @return ResponseEntity
     */
    @PostMapping(value = "{accountId}/deposits", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deposit(@PathVariable Long accountId, @RequestBody AmountDto amountDto){

        Account account=accountService.deposit(accountId, amountDto.getAmount());

        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage(AppConstants.SUCCESS_MSG);
        responseDto.setResponseBody(account);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    /**
     * This endpoint withdraws money from a given account
     *
     * @param accountId Long
     * @param amountDto AmountDto
     * @return ResponseEntity
     */
    @PostMapping(value = "{accountId}/withdraws", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> withdraw(@PathVariable Long accountId, @RequestBody AmountDto amountDto){

        Account account=accountService.withdraw(accountId, amountDto.getAmount());

        ResponseDto responseDto=new ResponseDto();
        responseDto.setMessage(AppConstants.SUCCESS_MSG);
        responseDto.setResponseBody(account);
        return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
