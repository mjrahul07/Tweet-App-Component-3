package com.tweetapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.LoggedUser;
import com.tweetapp.repository.LoggedUserRepository;


@Service
public class LoggedUserService {



	@Autowired
	LoggedUserRepository repository;
	
	public LoggedUser createLoggedUser(Date loginTime, String username) {
		
		return repository.save(new LoggedUser(loginTime, username));
		
	}

     public void deleteLoggedUser(String email) {
		
		repository.deleteByEmailId(email);
		
	}
	
	
}
