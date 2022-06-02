package com.CustomerMicroservice.CustomerMicroservice.Exception.exception;

public class LoginFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginFailedException() {
		super();
	}

	public LoginFailedException(String message) {
		super(message);
	}


}
