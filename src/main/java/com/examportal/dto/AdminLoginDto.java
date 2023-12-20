package com.examportal.dto;

public class AdminLoginDto {
	
	
	
	private String adminMail;	
	private String adminPassword;
	public AdminLoginDto() {
		super();
	}
	public AdminLoginDto(String adminMail, String adminPassword) {
		super();
		this.adminMail = adminMail;
		this.adminPassword = adminPassword;
	}
	public String getAdminMail() {
		return adminMail;
	}
	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "AdminLoginDto [adminMail=" + adminMail + ", adminPassword=" + adminPassword + "]";
	}
	

}
