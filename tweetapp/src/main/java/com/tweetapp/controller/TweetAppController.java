package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import com.tweetapp.customException.UserAlreadyExist;
import com.tweetapp.customException.UserNotFoundException;
import com.tweetapp.helperClass.UserHelperClass;
import com.tweetapp.helperClass.ForgetPasswordHelperClass;
import com.tweetapp.helperClass.MessageClass;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/v1.0/tweets")
@CrossOrigin(origins = "http://localhost:4200")
public class TweetAppController {

	@Autowired
	TweetService tweetService;
	@Autowired
	UserService userService;
	
	@Autowired
	private KafkaTemplate defaultKafkaTemplate;

	@RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
	public String healthCheck() {
		return "Healthy";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public UserHelperClass register(@RequestBody User user) {

	
			return userService.createUser(user);
		
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public UserHelperClass login(@RequestParam String username, @RequestParam String password) {

			return userService.loginUser(username, password);
		
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.PUT)
	public User forgetPassword(@RequestBody ForgetPasswordHelperClass forgetPasswordObj) {
		return userService.forgetPassword(forgetPasswordObj.getUsername(), forgetPasswordObj.getNewPassword());
	}

	@RequestMapping(value = "/users/allUsers", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.viewUsers();
	}

	@RequestMapping(value = "/allTweets", method = RequestMethod.GET)
	public List<Tweet> getAllTweets() {
		return tweetService.viewAllTweet();
	}

	@RequestMapping(value = "/user/searchUser/{username}", method = RequestMethod.GET)
	public User getUserByUserName(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public List<Tweet> getAllTweetsOfAUser(@PathVariable String username) {
		return tweetService.viewMyTweet(username);
	}

	@RequestMapping(value = "/addTweet", method = RequestMethod.POST)
	public Tweet postNewTweet(@RequestBody MessageClass messageObj) {
		//defaultKafkaTemplate.send("tweet_app_topic",messageObj.getMessage());
		return tweetService.createTweet(messageObj.getUsername(), messageObj.getMessage());
	}

	@RequestMapping(value = "/updateTweet/{id}", method = RequestMethod.PUT)
	public Tweet updateTweet(@PathVariable String id,
			@RequestBody MessageClass message) {
		return tweetService.updateTweet(message.getUsername(), id, message.getMessage());
	}

	@RequestMapping(value = "/{username}/deleteTweet/{id}", method = RequestMethod.DELETE)
	public MessageClass deleteTweet(@PathVariable String username, @PathVariable String id) {
		return tweetService.deleteTweet(username, id);
	}

	@RequestMapping(value = "/{username}/likeTweet/{id}", method = RequestMethod.PUT)
	public Tweet likeTweet(@PathVariable String username, @PathVariable String id) {
		return tweetService.likeTweet(username, id);
	}
	
//	@RequestMapping(value = "/publish/{publish}", method = RequestMethod.GET)
//	public String publish(@PathVariable String publish){
//		defaultKafkaTemplate.send("tweet_app_topic", publish);
//		return publish;
//	}

//	@RequestMapping(value = "/{username}/replyToTweet/{id}", method = RequestMethod.POST)
//	public String replyToTweet(@PathVariable String username, @PathVariable String id) {
//		return "User";
//	}

}
