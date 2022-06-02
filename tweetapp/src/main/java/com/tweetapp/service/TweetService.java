package com.tweetapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tweetapp.customException.TweetNotFoundException;
import com.tweetapp.customException.UserNotFoundException;
import com.tweetapp.helperClass.MessageClass;
import com.tweetapp.kafka.Producer;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;

@Service
public class TweetService {

	@Autowired
	TweetRepository tweetRepository;
	@Autowired
	UserRepository repository;
	@Autowired
	Producer producer;
	public Tweet createTweet(String username, String message) {

		User user = repository.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("User not Found");
		}

		Date date = new Date();
		int noOfLikes = 0;
		producer.sendMessage(message);
		return tweetRepository.save(new Tweet(date, username, message, noOfLikes));

	}

	public List<Tweet> viewAllTweet() {
		List<Tweet> listOfTweet = tweetRepository.findAll();
		if (listOfTweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}

		return listOfTweet;

	}

	public List<Tweet> viewMyTweet(String username) {

		List<Tweet> listOfTweet = tweetRepository.findByUsername(username);
		if (listOfTweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}

		return listOfTweet;

	}

	public Tweet updateTweet(String username, String Id, String message) {
		Tweet tweet = tweetRepository.findByUsernameAndId(username, Id);
		if (tweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}

		tweet.setMessage(message);
		return tweetRepository.save(tweet);

	}

	public MessageClass deleteTweet(String username, String Id) {

		Tweet tweet = tweetRepository.findByUsernameAndId(username, Id);
		if (tweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}

		tweetRepository.deleteById(Id);
		
		MessageClass message = new MessageClass(username,"Tweet"+ tweet.getId()+ "Deleted Successfully");
		return message;
	}


	public Tweet likeTweet(String username, String id) {
		Tweet tweet = tweetRepository.findByUsernameAndId(username, id);
		if (tweet == null) {
			throw new TweetNotFoundException("No Tweet Found");
		}
		int currentNoOfLikes = tweet.getNoOfLikes();
		int updatedNoOfLikes = currentNoOfLikes + 1;
		tweet.setNoOfLikes(updatedNoOfLikes);
		return tweetRepository.save(tweet);

	}

}
