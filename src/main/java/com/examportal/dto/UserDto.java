package com.examportal.dto;

public class UserDto {
	private String userMail;
	private String userName;
	private long userMobileNo;
	private String userAddress;
	public UserDto() {
		
	}
	public UserDto(String userMail,String userName, long userMobileNo, String useraddress) {
		super();
		this.userMail=userMail;
		this.userName = userName;
		this.userMobileNo = userMobileNo;
		this.userAddress = userAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUsername(String userName) {
		this.userName = userName;
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	@Override
	public String toString() {
		return "UserDto [userMail=" + userMail + ", userName=" + userName + ", userMobileNo=" + userMobileNo
				+ ", userAddress=" + userAddress + "]";
	}
	

}
