package com.CustomerMicroservice.CustomerMicroservice.Exception.exception;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tweetapp.model.CustomErrorResponse;





@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<CustomErrorResponse> handleConsumerNotFoundException(UserNotFoundException ex) {

		CustomErrorResponse response = new CustomErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setReason("Invalid Login details Provided");

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler({ UserAlreadyExist.class })
	public ResponseEntity<CustomErrorResponse> handleConsumerExistException(UserAlreadyExist ex) {

		CustomErrorResponse response = new CustomErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		response.setReason("User Already Exist with the provided Username");

		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);

	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<CustomErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {

		CustomErrorResponse response = new CustomErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.CONFLICT);
		response.setReason("Access Denied");

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);

	}
	
	@ExceptionHandler({ LoginFailedException.class })
	public ResponseEntity<CustomErrorResponse> handleLoginFailedException(AccessDeniedException ex) {

		CustomErrorResponse response = new CustomErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.CONFLICT);
		response.setReason("Login Failed");

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(BindException.class)
	protected HashMap<String, Object> handleBindException(BindException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		HashMap<String, Object> map = new HashMap<>();
		map.put("key1", "Validation Error");
		map.put("Exception", ex);
		map.put("status", status);
		return map;
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		HashMap<String, Object> map = new HashMap<>();
		map.put("key1", "Validation Error");
		map.put("Exception", e);
		map.put("status", status);
		return map;
	}
}
