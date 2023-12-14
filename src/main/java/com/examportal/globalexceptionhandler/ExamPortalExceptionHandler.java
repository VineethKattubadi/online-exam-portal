package com.examportal.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.examportal.exception.InvalidCredentialsException;
import com.examportal.exception.UnAcceptableInformationException;
import com.examportal.exception.UserNotFoundException;
import com.examportal.exception.UserUpdateException;


@ControllerAdvice
public class ExamPortalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(InvalidCredentialsException.class)
	public  ResponseEntity<String> invalidCredentialsExceptionHandler(InvalidCredentialsException ex) {
		System.out.println(ex);
		return new ResponseEntity<String>("Invalid Credentials ",HttpStatus.NO_CONTENT);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException ex){
		System.out.println(ex);
		return new ResponseEntity<String>("Invalid Email",HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(UserUpdateException.class)
	public ResponseEntity<String> userUpdateExceptionHandler(UserUpdateException ex){
		System.out.println(ex);
		return new ResponseEntity<String>("Invalid Data Fields",HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(UnAcceptableInformationException.class)
	public ResponseEntity<String> unAcceptableInformationException(UnAcceptableInformationException ex){
		System.out.println(ex);
		return new ResponseEntity<String>("Invalid Data Fields",HttpStatus.NOT_FOUND);
		
	}
	
	

}
