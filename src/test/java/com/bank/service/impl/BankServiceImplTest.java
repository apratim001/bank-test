package com.bank.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bank.model.Account;
import com.bank.model.FundTransferRequest;
import com.bank.model.FundTransferResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BankServiceImplTest {
	
	@Mock
	private CacheComponent cacheComponent;
	
	@InjectMocks
	private BankServiceImpl service;
	
	
	Map<Long, Map<Long, Account>> accountCache(){
		
		Map<Long, Map<Long, Account>> map =  new HashMap<Long, Map<Long,Account>>();
		Map<Long, Account> accountMap = new HashMap<>();
		Map<Long, Account> accountMap1 = new HashMap<>();

		
		Account acc = new Account();
		acc.setAccountbalance(100.0);
		acc.setActivestatus(true);
		
		Account acc1 = new Account();
		acc1.setAccountbalance(200.0);
		acc1.setActivestatus(true);

		accountMap.put(101l, acc);
		map.put(10101l, accountMap);
		
		accountMap1.put(202l, acc1);
		map.put(20202l, accountMap1);
		
		return map;
	}
	
	@Test
	public void testgetAllAccounts() {
		
		Mockito.when(cacheComponent.getAccountCache()).thenReturn(accountCache());
		
		List<Account> accList = service.getAllAccounts(10101l);
		
		Assertions.assertThat(accList.size()).isEqualTo(1);
	}
	
	@Test
	public void testgetAccount() {
		
		Mockito.when(cacheComponent.getAccountCache()).thenReturn(accountCache());
		
		Account acc = service.getAccount(10101l,101l);
		
		Assertions.assertThat(acc).isNotNull();
	}
	
	@Test
	public void testTransferFunds() {
		
		Mockito.when(cacheComponent.getAccountCache()).thenReturn(accountCache());
		
		FundTransferResponse response = service.transferFund(new FundTransferRequest(101l,10101l,"USD",1.0,202l,20202l));
		
		Assertions.assertThat(response).isNotNull();
	}

}
