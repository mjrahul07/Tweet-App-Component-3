package com.tweetapp.helperClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MessageClassTest {
	@Test
	public void messageClassTest() {
		MessageClass messageClass = new MessageClass("user","hi");
		assertEquals("user",messageClass.getUsername());
		assertEquals("hi",messageClass.getMessage());
		
	}

}
