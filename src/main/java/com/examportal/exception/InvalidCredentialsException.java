package com.examportal.exception;

public class InvalidCredentialsException extends RuntimeException{
	private String message;
	public InvalidCredentialsException() {
		
	}
	public InvalidCredentialsException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}

}

