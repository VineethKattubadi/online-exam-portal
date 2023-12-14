package com.examportal.dto;

import com.examportal.entity.User;

public class UserLoginDto {
	private String userMail;
	private String userPassword;
	public UserLoginDto (){
		
	}
	public UserLoginDto(String userMail, String userPassword) {
		super();
		this.userMail = userMail;
		this.userPassword = userPassword;
	}
	
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserPassword() {
		return userPassword; 
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	

}
