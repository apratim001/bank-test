package com.bank.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.exception.AccountNotFoundException;
import com.bank.exception.BadRequestException;
import com.bank.model.ErrorResponse;

@ControllerAdvice
public class BankControllerAdvice {
	
	Logger logger = LoggerFactory.getLogger(BankControllerAdvice.class);

	
	@ExceptionHandler(value = {BadRequestException.class})
	ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException exception) {
		
		String ticketId=UUID.randomUUID().toString();
		logger.error("Bad Request Received for {} with message {} | Ticket Raised to Track with ID {}",
				exception.getSource(),exception.getMessage(),ticketId);
		//Log ticket to some datasource
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getSource(),
				ticketId), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {AccountNotFoundException.class})
	ResponseEntity<ErrorResponse> handleAccountNotFound(AccountNotFoundException exception) {
		
		String ticketId=UUID.randomUUID().toString();
		logger.error("Account Not Found Exception happened for {} with message {} | Ticket Raised to Track with ID {}",
				exception.getSource(),exception.getMessage(),ticketId);
		//Log ticket to some datasource
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getSource(),
				ticketId), HttpStatus.NOT_FOUND);
	}

}
