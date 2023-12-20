package com.examportal.dto;

import jakarta.persistence.Column;

public class AllUsersDto {
	private int userId;
	
	
	private String userName;
	
	private String userMail;
	
	private long userMobileNo;
	
	private String userAddress;

	public AllUsersDto() {
		super();
	}

	public AllUsersDto(int userId, String userName, String userMail, long userMobileNo, String userAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.userMobileNo = userMobileNo;
		this.userAddress = userAddress;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public long getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(long userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	


}
