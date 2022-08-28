package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {
  private static final long serialVersionUID = -5218143265247846948L;

  String source;
  
  public AccountNotFoundException(String message,String source) {
    super(message);
    this.source=source;
  }

public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}
  
  
}
