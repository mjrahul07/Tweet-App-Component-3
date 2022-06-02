package com.tweetapp.helperClass;

import lombok.Data;

@Data
public class MessageClass {
	String username;
	String message;
	public MessageClass(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
	
	
    
}
