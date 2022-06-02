package com.tweetapp.helperClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ForgetPasswordHelperTest {
		
	
	@Test
	public void testForgetPasswordHelper() {
		ForgetPasswordHelperClass forgetPasswordHellperClass = new ForgetPasswordHelperClass();
		forgetPasswordHellperClass.setNewPassword("123");
		forgetPasswordHellperClass.setUsername("user");
		assertEquals("123",forgetPasswordHellperClass.getNewPassword());
		assertEquals("user",forgetPasswordHellperClass.getUsername());
	}
}
