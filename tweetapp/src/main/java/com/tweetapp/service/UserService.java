package com.tweetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.customException.UserAlreadyExist;
import com.tweetapp.customException.UserNotFoundException;
import com.tweetapp.helperClass.UserHelperClass;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public UserHelperClass createUser(User user) {

			boolean checkEmailExist = checkEmailExist(user.getEmail());
			boolean checkUsernameExist = checkUsernameExist(user.getUsername());
			if(checkEmailExist) {
				throw new UserAlreadyExist("User with this email already exist !!!");
			}
			
			if(checkUsernameExist) {
				throw new UserAlreadyExist("User with this username already exist !!!");
			}
			User userObj = repository.save(user);
		    UserHelperClass userHelperObj = new UserHelperClass(userObj.getId(),userObj.getEmail(), userObj.getUsername());
		    System.out.print(userObj);
		    return userHelperObj;
		    

	}

	public UserHelperClass loginUser(String username, String password) {

		User user = repository.findByUsernameAndPassword(username, password);
		if(user == null) {
			throw new UserNotFoundException("Invalid Login Details");
		}
		UserHelperClass userHelperObj = new UserHelperClass(user.getId(),user.getEmail(), user.getUsername());
		
		return userHelperObj;
	}

	public boolean checkEmailExist(String email) {
		User user = repository.findByEmail(email);
		if(user == null) {
			return false;
		}
		return true;
		
	}

	public boolean checkUsernameExist(String username) {

		User user = repository.findByUsername(username);
		if(user == null) {
			return false;
		}
		return true;
		
	}

	public List<User> viewUsers() {

		List<User> userList = repository.findAll();
		return userList;
	}

	public User resetPassword(String loginId, String newPassword) {
		User user = repository.findByUsername(loginId);
		if(user == null) {
			throw new UserNotFoundException("User not Found");
		}
		user.setPassword(newPassword);
		return repository.save(user);

	}

	public User getUserByUsername(String username) {
		User user = repository.findByUsername(username);
		if(user == null) {
			throw new UserNotFoundException("User not Found");
		}
		return user;
	}

	public User forgetPassword(String username, String newPassword) {
		User user = repository.findByUsername(username);
		if(user==null) {
			throw new UserNotFoundException("User not Found");
		}
		user.setPassword(newPassword);
		System.out.println(user.getPassword());
		return repository.save(user);
	}
}
