package com.bank.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bank.model.Account;
import com.bank.model.FundTransferRequest;
import com.bank.model.FundTransferResponse;
import com.bank.service.BankService;
import com.fasterxml.jackson.databind.ObjectMapper; 

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BankControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
    private BankController bankController;

	@Before
	public void setUp() {
		   mockMvc = MockMvcBuilders.standaloneSetup(bankController).build();
	   }

	@Mock
	private BankService bankService;

	@Test
	public void testGetAllAccounts() throws Exception {

		Mockito.when(bankService.getAllAccounts(Mockito.any())).thenReturn(new ArrayList<>());

		mockMvc
				.perform(MockMvcRequestBuilders.get("/getAllAccounts/{locationCode}",1)
						)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void testGetAccount() throws Exception {

		Mockito.when(bankService.getAccount(Mockito.any(),Mockito.any())).thenReturn(new Account());

		mockMvc
				.perform(MockMvcRequestBuilders.get("/getAccount/{locationCode}/{accountNumber}",1,1)
						)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
	
	@Test
	public void testTransferFund() throws Exception {

		Mockito.when(bankService.transferFund(Mockito.any()))
		.thenReturn(new FundTransferResponse("Test", "Test", 1l, LocalDateTime.now()));

		String input = new ObjectMapper().writeValueAsString(new FundTransferRequest(1l, 2l, "USD", 1.00, 3l, 4l));
		mockMvc
				.perform(MockMvcRequestBuilders.post("/transferFund")
						.contentType("application/json")
						.content(input))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}

}
