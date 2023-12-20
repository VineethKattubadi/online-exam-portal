package com.examportal.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.examportal.exception.InvalidCredentialsException;
import com.examportal.exception.QuestionsServiceException;
import com.examportal.exception.TestServiceException;
import com.examportal.exception.UnAcceptableInformationException;
import com.examportal.exception.UserNotFoundException;
import com.examportal.exception.UserServiceException;
import com.examportal.exception.UserUpdateException;

@ControllerAdvice
public class ExamPortalExceptionHandler {
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredentialsExceptionHandler(InvalidCredentialsException ex) {
		System.out.println(ex);
		return new ResponseEntity<String>("Invalid Credentials ", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException ex) {
		System.out.println(ex);
		return new ResponseEntity<String>("Invalid Email", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(UserUpdateException.class)
	public ResponseEntity<String> userUpdateExceptionHandler(UserUpdateException ex) {
		System.out.println(ex);
		return new ResponseEntity<String>("Invalid Data Fields", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(UnAcceptableInformationException.class)
	public ResponseEntity<String> unAcceptableInformationException(UnAcceptableInformationException ex) {
		System.out.println(ex);
		return new ResponseEntity<String>("Invalid Data Fields", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(QuestionsServiceException.class)
	public ResponseEntity<String> handleQuestionsServiceException(QuestionsServiceException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TestServiceException.class)
	public ResponseEntity<String> handleTestServiceException(TestServiceException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<String> handleUserServiceException(UserServiceException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	
}
