package com.examportal.dto;

public class UserRegistrationDto {
	private String userName;
	private int userId;
	private String userMail;
	private long userMobileNo;
	private String userAddress;
	private String userPassword;
	
	public UserRegistrationDto() {
		
	}

	public UserRegistrationDto(String userName, int userId, String userMail, long userMobileNo, String userAddress,
			String userPassword) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.userMail = userMail;
		this.userMobileNo = userMobileNo;
		this.userAddress = userAddress;
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public void setUserMobileNumber(long userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "UserRegistrationDto [userName=" + userName + ", userId=" + userId + ", userMail=" + userMail
				+ ", userMobileNo=" + userMobileNo + ", userAddress=" + userAddress + ", userPassword=" + userPassword
				+ "]";
	}
	

}
