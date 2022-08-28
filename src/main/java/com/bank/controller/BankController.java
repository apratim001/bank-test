package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.Account;
import com.bank.model.FundTransferRequest;
import com.bank.model.FundTransferResponse;
import com.bank.service.BankService;

@RestController
public class BankController {
  private final BankService bankService;

  @Autowired
  public BankController(BankService bankService) {
    this.bankService = bankService;
  }

  @RequestMapping(value = "/getAllAccounts/{locationCode}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<Account> getAllAccounts(@PathVariable Long locationCode) {
    return bankService.getAllAccounts(locationCode);
  }

  @GetMapping(value = "/getAccount/{locationCode}/{accountNumber}")
  @ResponseStatus(HttpStatus.OK)
  public Account getAccount(@PathVariable(value="locationCode") Long locationCode, 
		  @PathVariable(value="accountNumber") Long accountNumber) {
    return bankService.getAccount(locationCode,accountNumber);
  }
  
  @PostMapping(value = "/transferFund")
  @ResponseStatus(HttpStatus.OK)
  public FundTransferResponse transferFund(@RequestBody FundTransferRequest transferRequest) {
    return bankService.transferFund(transferRequest);
  }
}
