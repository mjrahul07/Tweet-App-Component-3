package com.CustomerMicroservice.CustomerMicroservice.Exception.exception;

public class UserAlreadyExistWithThatUsername extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2862505141325062716L;

	public UserAlreadyExistWithThatUsername() {
		super();
	}

	public UserAlreadyExistWithThatUsername(String message) {
		super(message);
	}

}
