package com.bank.service;

import com.bank.model.Account;
import com.bank.model.FundTransferRequest;
import com.bank.model.FundTransferResponse;

import java.util.List;

public interface BankService {
	List<Account> getAllAccounts(Long locationCode);

	Account getAccount(Long locationCode, Long accountNumber);

	FundTransferResponse transferFund(FundTransferRequest transferRequest);

}
