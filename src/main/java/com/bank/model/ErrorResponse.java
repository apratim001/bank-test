package com.bank.model;

public class ErrorResponse {
	
	private String message;
	
	private String source;
	
	private String ticketNumber;

	public ErrorResponse(String message, String source, String ticketNumber) {
		super();
		this.message = message;
		this.source = source;
		this.ticketNumber = ticketNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
}
