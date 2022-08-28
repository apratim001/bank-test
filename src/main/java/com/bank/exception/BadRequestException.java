package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
  private static final long serialVersionUID = 6171978443681353425L;

  private String source;
  
  public BadRequestException(String message,String source) {
    super(message);
    this.source = source;
  }

public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}
  
  
}
