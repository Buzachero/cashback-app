package com.buzachero.app.cashback.services.exceptions;

public class InvalidPeriod extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidPeriod(String msg) {
		super(msg);
	}
	
	public InvalidPeriod(String msg, Throwable cause) {
		super(msg, cause);
	}

}
