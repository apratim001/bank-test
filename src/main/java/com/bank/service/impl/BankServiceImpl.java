package com.bank.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.exception.AccountNotFoundException;
import com.bank.exception.BadRequestException;
import com.bank.model.Account;
import com.bank.model.FundTransferRequest;
import com.bank.model.FundTransferResponse;
import com.bank.service.BankService;

@Service
class BankServiceImpl implements BankService {


	private final CacheComponent cacheComponent;
	
	Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

	@Autowired
	BankServiceImpl(CacheComponent cacheComponent) {
		this.cacheComponent = cacheComponent;
	}

	@Override
	public List<Account> getAllAccounts(Long locationCode) {
		logger.info("Started with Get All Accounts from Cache at {} ", LocalDateTime.now());

		validateGetAllAccounts(locationCode);
		List<Account> accountList = cacheComponent.getAccountCache().getOrDefault(locationCode, new HashMap<>()).values().stream()
				.collect(Collectors.toList());
		logger.info("Finished with Get All Accounts from Cache at {} ", LocalDateTime.now());

		if (accountList.size() == 0) {
			throw new AccountNotFoundException("No Accounts found for this Location Code","getAllAccounts");
		}

		return accountList;
	}

	@Override
	public Account getAccount(Long locationCode, Long accountNumber) {

		logger.info("Started with Get Accounts from Cache at {} ", LocalDateTime.now());

		validateGetAccount(locationCode, accountNumber);

		Account account = cacheComponent.getAccountCache().get(locationCode).get(accountNumber);

		logger.info("Finished with Get Accounts from Cache at {} ", LocalDateTime.now());

		if (account == null) {
			throw new AccountNotFoundException("No Account found for this Location Code and Account Number","getAccount");
		}
		return account;
	}

	

	@Override
	public FundTransferResponse transferFund(FundTransferRequest transferRequest) {

		logger.info("Starting with Transfer Funds from Account at {} ", LocalDateTime.now());
		
		validateTransferFunds(transferRequest);

		Account sourceAccount = cacheComponent.getAccountCache().get(transferRequest.getSourceLocationcode())
				.get(transferRequest.getSourceAccountnumber());

		validateSourceAccount(sourceAccount, transferRequest.getAmount());

		Account destinationAccount = cacheComponent.getAccountCache().get(transferRequest.getDestinationLocationcode())
				.get(transferRequest.getDestinationAccountnumber());

		validateDestinationAccount(destinationAccount);

		Long transId = new Random(0).nextLong();

		CompletableFuture<Account> updateFutureDest = CompletableFuture.supplyAsync(()-> updateAccountAmount(destinationAccount,transferRequest.getAmount(),transId));
		
		CompletableFuture<Account> updateFutureSource = CompletableFuture.supplyAsync(()-> updateAccountAmount(sourceAccount,transferRequest.getAmount()*-1,transId));

		try {
			CompletableFuture.allOf(updateFutureDest,updateFutureSource).get();
		} catch (Exception e) {
			FundTransferResponse response = new FundTransferResponse("Failed", "Funds Transfer Failed", transId, LocalDateTime.now());
			return response;
		}
		
		FundTransferResponse response = new FundTransferResponse("Completed", "Funds Transfered Successfully", transId, LocalDateTime.now());
		
		logger.info("Finished with Transfer Funds from Account at {} ", LocalDateTime.now());

		return response;
	}

	private Account updateAccountAmount(Account account, Double amount, Long transId) {
		
		account.setAccountbalance(account.getAccountbalance()+amount);
		
		FundTransferResponse response = new FundTransferResponse("Completed", "Funds Transfer Amount = "+amount+" "+account.getCurrency(), transId, LocalDateTime.now());
		account.getTransactionList().add(response);
		
		cacheComponent.getAccountCache().get(account.getLocationcode()).put(account.getAccountnumber(), account);
		
		return account;
		
		
	}

	private void validateTransferFunds(FundTransferRequest transferRequest) {

		validateGetAccount(transferRequest.getSourceLocationcode(), transferRequest.getSourceAccountnumber());

		validateGetAccount(transferRequest.getDestinationLocationcode(), transferRequest.getDestinationAccountnumber());

		if(transferRequest.getAmount() <= 0d) {
			throw new BadRequestException("Invalid Transfer Amount","TransferFunds");
		}
	}

	private void validateDestinationAccount(Account destinationAccount) {

		if (destinationAccount == null) {
			throw new AccountNotFoundException("Destination Account Doesnot Exist!","TransferFunds");
		}

		validateAccountStatus(destinationAccount);

	}

	private void validateAccountStatus(Account account) {
		if (!account.getActivestatus()) {
			throw new BadRequestException("Account is Not Active anymore!","TransferFunds");
		}
	}

	private void validateSourceAccount(Account sourceAccount, Double amount) {

		if (sourceAccount == null) {
			throw new AccountNotFoundException("Source Account Doesnot Exist!","TransferFunds");
		}

		validateAccountStatus(sourceAccount);

		if (amount >= sourceAccount.getAccountbalance()) {
			throw new BadRequestException("Funds Exceed Transferable Limit","TransferFunds");
		}

	}

	private void validateGetAccount(Long locationCode, Long accountNumber) {

		validateLocationCode(locationCode,"GetAccount");

		validateAccountNumber(accountNumber,"GetAccount");

		validateLocCodeAccNum(locationCode, accountNumber,"GetAccount");

	}

	private void validateLocCodeAccNum(Long locationCode, Long accountNumber,String source) {
		if ((locationCode == null || locationCode == 0) && (accountNumber == null || accountNumber == 0)) {
			throw new BadRequestException("Invalid Location code and Account Number",source);
		}
	}

	private void validateAccountNumber(Long accountNumber,String source) {
		if (accountNumber == null || accountNumber == 0) {
			throw new BadRequestException("Invalid Account Number",source);
		}
	}

	private void validateLocationCode(Long locationCode,String source) {
		if (locationCode == null || locationCode == 0) {
			throw new BadRequestException("Invalid Location code",source);
		}
	}

	private void validateGetAllAccounts(Long locationCode) {

		validateLocationCode(locationCode,"GetAllAccounts");

	}

}
