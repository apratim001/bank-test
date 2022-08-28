package com.bank.model;

import java.io.Serializable;

public class FundTransferRequest implements Serializable {


	private static final long serialVersionUID = 1L;

	private Long sourceAccountnumber;

	private Long sourceLocationcode;
	
	private String sourceCurrency;
	
	private Double amount;

	private Long destinationAccountnumber;

	private Long destinationLocationcode;

	public Long getSourceAccountnumber() {
		return sourceAccountnumber;
	}

	public void setSourceAccountnumber(Long sourceAccountnumber) {
		this.sourceAccountnumber = sourceAccountnumber;
	}

	public Long getSourceLocationcode() {
		return sourceLocationcode;
	}

	public void setSourceLocationcode(Long sourceLocationcode) {
		this.sourceLocationcode = sourceLocationcode;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getDestinationAccountnumber() {
		return destinationAccountnumber;
	}

	public void setDestinationAccountnumber(Long destinationAccountnumber) {
		this.destinationAccountnumber = destinationAccountnumber;
	}

	public Long getDestinationLocationcode() {
		return destinationLocationcode;
	}

	public void setDestinationLocationcode(Long destinationLocationcode) {
		this.destinationLocationcode = destinationLocationcode;
	}

	public FundTransferRequest(Long sourceAccountnumber, Long sourceLocationcode, String sourceCurrency, Double amount,
			Long destinationAccountnumber, Long destinationLocationcode) {
		super();
		this.sourceAccountnumber = sourceAccountnumber;
		this.sourceLocationcode = sourceLocationcode;
		this.sourceCurrency = sourceCurrency;
		this.amount = amount;
		this.destinationAccountnumber = destinationAccountnumber;
		this.destinationLocationcode = destinationLocationcode;
	}

	public FundTransferRequest() {
		super();
	}
	
	
	
}
