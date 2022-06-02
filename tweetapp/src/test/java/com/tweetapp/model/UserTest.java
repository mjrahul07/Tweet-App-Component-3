package com.tweetapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	public void userTest() {
		User user = new User();
		user.setContactNumber(123123123);
		user.setEmail("user@email.com");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setId("1");
		user.setPassword("123123");
		assertEquals("1",user.getId());
		assertEquals("user@email.com",user.getEmail());
		assertEquals("fname",user.getFirstName());
		assertEquals("lname",user.getLastName());
		assertEquals("123123",user.getPassword());
		assertEquals(123123123,user.getContactNumber());
		
	}

}
