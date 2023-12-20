package com.examportal.exception;

public class UserServiceException extends RuntimeException {
	private String message;
	public UserServiceException() {
		
	}
	public UserServiceException(String message) {
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
		return "UserServiceException [message=" + message + "]";
	}

}