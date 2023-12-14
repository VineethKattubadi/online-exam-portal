package com.examportal.dto;

public class UserDto {
	private String userMail;
	private String userName;
	private long userMobileNo;
	private String Address;
	public UserDto() {
		
	}
	public UserDto(String userMail,String userName, long userMobileNo, String address) {
		super();
		this.userMail=userMail;
		this.userName = userName;
		this.userMobileNo = userMobileNo;
		Address = address;
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
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
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
				+ ", Address=" + Address + "]";
	}
	

}
