package com.tweetapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TweetTest{
	
	
		
	@Test
	public void testTweet() throws ParseException {
		
		 Tweet tweet = new Tweet();
		tweet.setId("1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
		tweet.setCreatedDateTime(dateWithoutTime);
		tweet.setMessage("Hi");
		tweet.setNoOfLikes(1);
		tweet.setUsername("JS");
		assertEquals("1",tweet.getId());
		assertEquals("Hi",tweet.getMessage());
		assertEquals(1,tweet.getNoOfLikes());
		assertEquals(dateWithoutTime,tweet.getCreatedDateTime());
	}
	
	
}