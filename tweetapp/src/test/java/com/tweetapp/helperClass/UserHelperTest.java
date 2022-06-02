package com.tweetapp.helperClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserHelperTest {
	@Test
	public void userHelperTest() {
		UserHelperClass userHelperClass = new UserHelperClass("1","user@email","user");
		assertEquals("user",userHelperClass.getUsername());
		assertEquals("user@email",userHelperClass.getEmail());
		assertEquals("1",userHelperClass.getId());
		
	}
}
