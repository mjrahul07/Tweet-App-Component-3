package com.tweetapp;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;


import com.tweetapp.service.LoggedUserService;

@SpringBootApplication
public class TweetappApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TweetappApplication.class, args);
	}
	
	

}