package com.bank.model;

import java.time.LocalDateTime;

public class FundTransferResponse {

	private String status;
	
	private String message;
	
	private Long transactionId;
	
	private LocalDateTime transactionTime;
	
	

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public FundTransferResponse(String status, String message, Long transactionId, LocalDateTime transactionTime) {
		super();
		this.status = status;
		this.message = message;
		this.transactionId = transactionId;
		this.transactionTime = transactionTime;
	}

	
}
