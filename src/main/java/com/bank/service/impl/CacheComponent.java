package com.bank.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;

@Component
public class CacheComponent {

	// Cache for storing Accounts
	// Format <BranchLocationNumber, <AccountNumber,Account>>
	Map<Long, Map<Long, Account>> accountsCache = new HashMap<Long, Map<Long, Account>>();
	
	@Autowired
	private AccountRepository accountRepository;
	
	Logger logger = LoggerFactory.getLogger(CacheComponent.class);

	
	@EventListener(ApplicationReadyEvent.class)
	public void setUpCache() {

		logger.info("Started with setting up Accounts Cache at {} ", LocalDateTime.now());

		accountRepository.findAll().stream().collect(Collectors.groupingBy(Account::getLocationcode)).entrySet()
				.forEach(item -> {

					Map<Long, Account> accountMap = item.getValue().stream()
							.collect(Collectors.toMap(Account::getAccountnumber, value -> value));

					accountsCache.put(item.getKey(), accountMap);
				});

		logger.info("Finished with setting up Accounts Cache at {} ", LocalDateTime.now());

	}
	
	public Map<Long, Map<Long, Account>> getAccountCache() {
		
		return accountsCache;
	}
}
